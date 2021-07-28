package org.starcoin.serde.format.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.starcoin.serde.format.ContainerFormat;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.starcoin.serde.format.jackson.utils.MappingUtils.toContainerFormatMap;

public class ReferenceUtils {
    private ReferenceUtils() {}


    public static Map<String, Object> includeExternalObjects(Map<String, Object> originMap,
                                                             ObjectMapper objectMapper,
                                                             List<Map<String, ContainerFormat>> externalContainerFormatMap,
                                                             List<Map<String, Object>> externalMaps) {
        //System.out.println(originMap);
        Map<String, ContainerFormat> containerFormatMap = toContainerFormatMap(objectMapper, originMap);
        return includeExternalObjects(originMap, containerFormatMap, externalContainerFormatMap, externalMaps);
    }

    public static Map<String, Object> includeExternalObjects(Map<String, Object> originMap,
                                                             Map<String, ContainerFormat> containerFormatMap,
                                                             List<Map<String, ContainerFormat>> externalContainerFormatMap,
                                                             List<Map<String, Object>> externalMaps) {
        //System.out.println(containerFormatMap);
        List<String> referencedNames = ReferenceUtils.getReferencedExternalContainerTypeNames(containerFormatMap, externalContainerFormatMap);
        List<Object> referencedValues = ReferenceUtils.findValuesByNames(referencedNames, externalMaps.toArray(new Map[0]));
        //System.out.println(referencedValues);
        Map<String, Object> concatenatedMap = Stream.concat(originMap.entrySet().stream(),
                IntStream.range(0, referencedNames.size())
                .mapToObj(n -> new AbstractMap.SimpleEntry<String, Object>(referencedNames.get(n), referencedValues.get(n)))
        ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        return concatenatedMap;
    }


    public static List<String> getReferencedExternalContainerTypeNames(Map<String, ContainerFormat> containerFormatMap,
                                                                       List<Map<String, ContainerFormat>> externalContainerFormatMap) {
        Stream<String> referencedExternalNameStream = containerFormatMap.entrySet().stream().flatMap(c -> {
            String name = c.getKey();
            //            System.out.println(String.format("===== %1$s =====", name));
            //            System.out.println(c.getValue().referencedContainerTypeNames());
            //            System.out.println(ReferenceUtils.getReferenceNamesRecursively(name, n ->
            //                    containerFormatMap1.get(n).referencedContainerTypeNames()
            //            ));
            List<Map<String, ContainerFormat>> allContainerMap = new ArrayList<>();
            allContainerMap.add(containerFormatMap);
            allContainerMap.addAll(externalContainerFormatMap);
            List<String> referencedNames = ReferenceUtils.getReferenceNamesRecursively(name, allContainerMap.toArray(new Map[0]));
            referencedNames.removeAll(containerFormatMap.keySet());
            //            System.out.println(referencedNames);
            return referencedNames.stream();
        });
        List<String> referencedNames = referencedExternalNameStream.collect(Collectors.toList());
        return referencedNames;
    }

    public static List<Object> findValuesByNames(List<String> names, Map<String, Object>... maps) {
        return names.stream().map(n -> Arrays.stream(maps).filter(m -> m.containsKey(n))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find name: " + n))
                .get(n)
        ).collect(Collectors.toList());
    }

    public static List<String> getReferenceNamesRecursively(String name, Map<String, ContainerFormat>... maps) {
        return ReferenceUtils.getReferenceNamesRecursively(name, n ->
                Arrays.stream(maps).filter(m -> m.containsKey(n)).findFirst()
                        //.orElse((Map<String, ContainerFormat>) Collections.EMPTY_MAP)
                        .orElseThrow(() -> new RuntimeException("Cannot find name: " + n))
                        .get(n).referencedContainerTypeNames()
        );
    }

    public static List<String> getReferenceNamesRecursively(String name,
                                                             Function<String, Iterable<String>> getReferencedNames) {
        List<String> names = new ArrayList<>();
        addReferencedNamesRecursively(names, name, getReferencedNames);
        return names;
    }

    public static void addReferencedNamesRecursively(Collection<String> referencedNames, String name,
                                                      Function<String, Iterable<String>> getReferencedNames) {
        for (String n : getReferencedNames.apply(name)) {
            if (!referencedNames.contains(n)) {
                referencedNames.add(n);
                addReferencedNamesRecursively(referencedNames, n, getReferencedNames);
            }
        }
    }

}
