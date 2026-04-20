# Mechanic Shop Manager

**A Java and SQL application to manage a mechanic workshop**

By Connor Bosco and Jayrajsinh Mahendrasinh Gohil

---

## Introduction

This command line interface application is our final project for CPRG 211 and a representation of what a tool for tracking vehicle maintenance orders might look like in the industry. It keeps track of customers, mechanic staff, vehicles, and orders, using MariaDB to store all the information in a database. While designed for Windows, its Java nature will likely allow it to run on MacOS and Linux as long as the database is properly set up.

## Model

This section of the application defines the customers, mechanics, vehicles, and work orders themselves. It provides a set of classes to the other systems to actually manage and integrate with the database.

## Data Access Object (DAO)

The DAO classes are what actually manage the database tables. Using the model classes to perform complex logic, they can create, edit, and delete entries for customers, mechanics, vehicles, and orders.

## Command Line Interface (CLI)

This works entirely out of the `Main` class and provides a readable and interactible interface to the user in their machine's terminal. By inputting numbers to select options in the text-based menu, the user can perform certain actions to manage the database in a user-friendly manner.