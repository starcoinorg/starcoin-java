/**
 * Module defining the Abstract Syntax Tree (AST) of Serde formats.
 *
 * @see <a href="https://github.com/Dinnerbone/serde-reflection/blob/master/serde-reflection/src/format.rs">serde-reflection/src/format.rs</a>
 */
package org.starcoin.serde.format;

// Copyright (c) Facebook, Inc. and its affiliates
// SPDX-License-Identifier: MIT OR Apache-2.0

//! Module defining the Abstract Syntax Tree (AST) of Serde formats.
//!
//! Node of the AST are made of the following types:
//! * `ContainerFormat`: the format of a container (struct or enum),
//! * `Format`: the format of an unnamed value,
//! * `Named<Format>`: the format of a field in a struct,
//! * `VariantFormat`: the format of a variant in a enum,
//! * `Named<VariantFormat>`: the format of a variant in a enum, together with its name,
//! * `Variable<Format>`: a variable holding an initially unknown value format,
//! * `Variable<VariantFormat>`: a variable holding an initially unknown variant format.