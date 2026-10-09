# VNM Compiler — Part II: Parser

### Syntax Analysis and Grammar Implementation Using JavaCC

A Java-based syntax parser for **VNM (Vecs'N'Mats)**, a domain-specific programming language designed around vector and matrix operations.
Developed as part of **CPS710 — Compilers** at **Toronto Metropolitan University**, this project extends the lexical scanner from Part I by introducing grammar-driven syntax analysis using **JavaCC (Java Compiler Compiler)**.
The project focuses on transforming a context-free grammar into a parser capable of recognizing valid VNM expressions and statements while detecting syntax errors.

## Project Overview

A compiler typically processes source code through several stages, including lexical analysis, syntax analysis, semantic analysis, and code generation.
This project focuses on the first two stages:

- **Lexical Analysis:** Converts source code into a sequence of tokens.
- **Syntax Analysis:** Determines whether the token sequence follows the grammatical rules of the VNM language.

Part I implemented the lexical scanner. Part II extends that foundation by defining and implementing the language grammar.
The parser is built using JavaCC, which generates Java-based parsing components from grammar specifications.

### Project Objectives

- Analyze and refine the provided VNM grammar.
- Identify and eliminate grammatical ambiguity.
- Remove direct and indirect left recursion.
- Preserve appropriate operator precedence and associativity.
- Transform the grammar into a form suitable for predictive parsing.
- Implement grammar productions using JavaCC.
- Apply lookahead where necessary to resolve parsing conflicts.
- Validate expressions and statements through automated testing.

## Technologies Used

- **Java:** Core implementation language.
- **JavaCC:** Scanner and parser generation.
- **Backus–Naur Form (BNF):** Formal representation of grammar productions.
- **GNU Make:** Automated project compilation.
- **GNU sed:** Translation of BNF productions into JavaCC-style functions.
- **Shell Scripting:** Automated test execution.
- **Git and GitHub:** Source control and project management.

## System Architecture

The VNM parser follows a structured compilation pipeline.

### 1. Source Input

The system accepts VNM source statements and expressions.

### 2. Lexical Analysis

The lexical scanner processes the source input and recognizes tokens, including:

- Keywords
- Identifiers
- Numeric literals
- String literals
- Operators
- Delimiters
- Boolean literals

The scanner provides tokens to the parser.

### 3. Syntax Analysis

The parser evaluates the token sequence against the VNM grammar.
Each grammar production defines a valid language construct.
The parser uses JavaCC-generated parsing functions to determine whether the input satisfies these grammatical rules.

### 4. Parsing Result

- **Valid syntax:** The parser reports successful parsing.
- **Invalid syntax:** The parser reports a parsing error.

This project focuses on syntax recognition rather than full program execution or machine-code generation.

## Grammar Design and Optimization

One of the primary objectives of Part II is to transform the original VNM grammar into a form suitable for JavaCC.
The supplied grammar is defined in: `grammarVNM.txt`

### Ambiguity Resolution

An ambiguous grammar allows a sentence to have more than one valid parse tree.
The grammar refinement process involves identifying ambiguous productions and restructuring them to establish a consistent interpretation.
Particular attention is given to expressions and statements that may share similar grammatical forms.

### Left Recursion Elimination

Recursive-descent parsers cannot directly process left-recursive productions without appropriate transformation.
The grammar must therefore be examined for:

- **Direct left recursion:** A production refers to itself as its first symbol.
- **Indirect left recursion:** Multiple productions form a left-recursive dependency.

The objective is to rewrite these productions while preserving the intended language structure.

### Operator Associativity

Operators of equal precedence should follow the appropriate associativity rules.
For example, subtraction is normally left-associative: `a - b - c`
This is interpreted as: `(a - b) - c`
Grammar restructuring helps ensure that expressions follow the intended evaluation structure.

### Predictive Parsing and Lookahead

JavaCC uses predictive parsing to select grammar alternatives.
The grammar is designed to minimize unnecessary lookahead while maintaining readability.
Where alternatives cannot be distinguished using a single token, JavaCC's `LOOKAHEAD` directive can be applied locally.
The project aims to achieve a reasonably small lookahead requirement, with LL(2) identified as a practical target in the assignment.

## Parser Implementation

The parser is implemented through the JavaCC grammar file: `VNM.jj`
This file contains the lexical definitions and parser productions.

### Stage 1: Grammar Refinement

The original grammar is examined and modified to:

- Reduce ambiguity.
- Remove left recursion.
- Resolve parsing conflicts.
- Preserve language semantics.
- Improve compatibility with JavaCC.

The modified grammar is maintained in BNF form.

### Stage 2: JavaCC Translation

The project includes a conversion script: `bnftojj.sed`
This script helps translate BNF grammar productions into JavaCC-compatible parsing functions.

The conversion command is: bash
sed -f bnftojj.sed < grammarVNM.txt > grammarCC.out

The generated productions can then be reviewed and integrated into `VNM.jj`.
Manual adjustments may be necessary for grammar alternatives, lookahead directives, and end-of-file handling.

### Parser Entry Point

The original grammar defines the entry production as: bnf
start ::= statement ";" | expression ";"

This production establishes the valid top-level forms accepted by the language parser. The implementation must also account for end-of-file conditions and syntax errors.

## Project Structure

The repository contains the following major components.

### Grammar and Parser Files

- **`VNM.jj`** — JavaCC grammar containing lexical specifications and parser productions.
- **`grammarVNM.txt`** — BNF grammar for the VNM language.
- **`bnftojj.sed`** — Script for translating BNF productions into JavaCC-compatible functions.
- **`VNM.java`** — JavaCC-generated parser class.
- **`VNMConstants.java`** — Token constants used by the generated parser.
- **`VNMTokenManager.java`** — Lexical token management.
- **`SimpleCharStream.java`** — Input character stream handling.

### Token Classes

- **`Token.java`** — Base token representation.
- **`NumberToken.java`** — Numeric token representation.
- **`StringToken.java`** — String token representation.
- **`IdNumToken.java`** — Numeric identifier token representation.
- **`IdBoolToken.java`** — Boolean identifier token representation.
- **`IdVecToken.java`** — Vector identifier token representation.
- **`IdMatToken.java`** — Matrix identifier token representation.

### Error Handling and Execution

- **`ParseException.java`** — Parsing exception handling.
- **`TokenMgrError.java`** — Lexical error reporting.
- **`TestVNM.java`** — Test interface for scanner and parser execution.

### Build and Testing

- **`makefile`** — Automated compilation instructions.
- **`runtests`** — Automated test suite execution.
- **`t`** — Individual test execution helper.
- **`tests/`** — Test inputs and expected results.
- **`.gitignore`** — Excludes compiled classes and IDE-generated files.

## Installation and Setup

### Prerequisites

The project requires:

- Java Development Kit (JDK)
- JavaCC
- GNU Make
- A Unix-compatible shell
- GNU sed

The project can be built in a Linux environment, including Ubuntu through Windows Subsystem for Linux (WSL).

### 1. Clone the Repository

bash
git clone https://github.com/akarshanrsingh/VNM-Compiler-Part2.git

Navigate into the project directory: bash
cd VNM-Compiler-Part2

### 2. Install Dependencies (Ubuntu)

bash
sudo apt update
sudo apt install -y default-jdk javacc make

### 3. Compile the Project

Use the supplied Makefile:
bash
make

The Makefile handles the project's configured build process.
Depending on the local JavaCC installation and Makefile configuration, environment-specific adjustments may be required.

### 4. Run the Parser

After successful compilation, launch the provided test interface:
bash
java -classpath . TestVNM

Enter VNM expressions or statements to test syntax recognition.

## Automated Testing

The project includes a test suite for validating the parser against predefined inputs and expected results.

Each test category contains files with the following extensions:

- **`.in`** — Input expressions or statements supplied to the parser.
- **`.expected`** — Expected output for comparison.
- **`.out`** — Actual output produced during testing.

### Running All Tests

Grant execution permissions to the test scripts:
bash
chmod u+x t runtests

Run the complete test suite:
bash
./runtests

The scripts compare generated results with the expected output and report differences.

### Test Categories

The repository includes test files organized under these names:

- **`simplestuff`**
- **`expressions`**
- **`conditions`**
- **`simplestats`**
- **`statements1`**
- **`statements2`**
- **`miscellaneous`**

These categories cover different sets of VNM language constructs and combinations of expressions and statements.

### Expected Behavior

For syntactically valid input, the parser is expected to report:
text
Parsing Succeeded

For invalid input, the parser should identify a syntax error rather than treating the input as valid.

### Comparing Test Results

Individual test outputs can be compared using:
bash
diff -u tests/expressions.expected tests/expressions.out

No output from `diff` indicates that the two files match.

**Testing status:** The repository contains the test framework and test cases. Complete test-suite results have not been independently verified in this README.

## Key Computer Science Concepts

This project demonstrates concepts from compiler construction and formal language theory.

### Context-Free Grammars

Grammar productions define the valid structure of expressions and statements.

### Recursive-Descent Parsing

Grammar productions are represented as parsing functions that recognize valid token sequences.

### LL(k) Parsing

Predictive parsing uses a limited number of lookahead tokens to determine which production to apply.

### Grammar Transformation

Ambiguity reduction, left recursion elimination, and grammar restructuring improve compatibility with predictive parsing.

### Lexical and Syntax Analysis Integration

The project connects token recognition with grammatical validation, demonstrating how early compiler stages work together.

### Automated Software Testing

Test inputs and expected outputs support repeatable verification of parser behavior.

## Academic Context

**Course:** CPS710 — Compilers  
**Institution:** Toronto Metropolitan University  
**Project:** VNM Interpreter — Part II: Parser  
**Language:** Java  
**Parser Generator:** JavaCC

This project is part of a multi-stage compiler construction assignment involving the VNM domain-specific language.
Part II builds upon the lexical scanner developed in Part I and introduces formal grammar analysis and syntax parsing.

### Related Project

**Part I — Lexical Scanner**

[View VNM Compiler Part I](https://github.com/akarshanrsingh/VNM-Compiler)

## Author

**Akarshan R Singh**  
Computer Engineering  
Toronto Metropolitan University

## Project Scope

This repository represents the parser stage of the CPS710 VNM interpreter project.
Its primary purpose is to demonstrate lexical and syntactic analysis using JavaCC, grammar transformation, predictive parsing, and automated testing.
It is not presented as a complete production compiler or a full VNM execution environment.
