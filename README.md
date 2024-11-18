# Android Project Application - Final Project ReadMe

## Project Overview

This repository contains the final project for the Android Application Development course. The goal of this project is to create a fully functional Android application that meets all specified requirements and demonstrates best practices in mobile app design and development.

---

## Project Features

### Screen Layouts
- **Settings/Setup Screen**: Collects user information such as:
  - Beginning weight
  - Height
  - Gender
  - Goal weight
  - Goal weight target date
- **Main Screen**: Displays statistics including:
  - Current weight
  - Body Mass Index (BMI)
  - Average weekly weight loss
  - Total weight loss to date
- **Weight Entry Screen**: Enables users to:
  - Enter weight and date
  - Save or cancel entries
- **History Page**: Displays a list of past weight entries and provides options to:
  - View details
  - Delete entries
- **Progress Picture Functionality**:
  - Allows users to take progress pictures and link them to weight entries.
  - Progress pictures are viewable and deletable along with the associated entry.

---

### Core Functionalities
- Navigation across all screens without issues.
- Statistics dynamically update based on user inputs.
- Weight and settings data are permanently stored in an SQLite database.
- Camera functionality integrated for progress pictures.
- Consistent theme across all screens.

---

### Database Integration
- Weight entries and settings are stored persistently in an SQLite database.
- Data remains accessible after the app is closed and reopened.

---

### High-Quality Code
- Code adheres to industry standards and is optimized for performance and readability.

---

## Development Requirements

- **Minimum Android SDK Version**: API 25 or higher.
- **Prototyping Tool**: [InVision](https://projects.invisionapp.com/d/signup)
- **Database**: SQLite
- **Code Standards**: Follow Android best practices.
