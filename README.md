Android Clean Architecture Project with MVVM, Retrofit, Gson, Detekt, Coroutine, Flow, and Coil

Project Description

This project demonstrates the implementation of clean architecture principles in an Android application, utilizing MVVM (Model-View-ViewModel) for separation of concerns and a layered approach for maintainability and testability.

Key Features

Clean Architecture: Adheres to clean architecture principles, ensuring loosely coupled layers for better testability and maintainability.
MVVM Pattern: Employs the MVVM pattern for clear separation of concerns between data, business logic, and UI presentation.
Retrofit and Gson: Leverages Retrofit for efficient network calls and Gson for seamless JSON parsing and deserialization.
Detekt: Integrates Detekt for static code analysis, helping identify potential issues and maintain code quality.
Coil: Utilizes Coil for efficient image loading and management within the application.
Product Listing with Search: Implements a UI that displays a list of products and provides a search bar for filtering based on user input.
Getting Started

Prerequisites:

Android Studio (latest stable version recommended)
An Android device or emulator configured for development
Cloning the Repository:

Bash
git clone https://github.com/your-username/Android-Clean-Architecture.git
Use code with caution.
content_copy
Replace your-username with your actual GitHub username.

Opening the Project in Android Studio:

Open Android Studio.
Click on "File" -> "Open" or "Import Projects".
Navigate to the cloned repository directory and select the project.
Click "Open".
Setting Up Dependencies:

The project's dependencies are managed using Gradle. Dependencies are declared in the build.gradle file located at the project level and in the app/build.gradle file for the application module.
Ensure you have a valid internet connection so that Gradle can download the required libraries.
Running the Application:

Click the "Run" button (green triangle) in the Android Studio toolbar.
Select a connected device or emulator from the device picker.
Click "OK" to start the application.
Project Structure

The project adheres to a clean architecture structure, with layers separated for better organization and maintainability:

data (Contains data access logic, repositories, and network layer)
domain (Houses core business logic and models)
presentation (Encompasses the UI layer, ViewModels, and Activities/Fragments)
Usage

Product Listing:

The application displays a list of products on launch.
You can interact with the list items as needed.
Product Search:

Use the provided search bar to filter the product list based on your input.
The search results will be dynamically updated as you type.
