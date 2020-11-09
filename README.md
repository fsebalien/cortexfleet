# CortexFleet Automation Case Study

- Web Test Automation Scenerio with Selenium, Java & Cucumber on Maven framework

Automated Scenerio: 
  Login scenerios for Cortex Fleet platform.
  
## Requirements

- Jdk 1.8
- Apache Maven 3.6.3

## Setup And Run

- To run both Integration and Web Automation features at your local environment, please run the commands below;

```mvn test```

- To run only tagged Regression case (happy path) at your local environment, please run the commands below;

```mvn test -Dcucumber.options="--tags @Regression"```

- After the maven build success, Cucumber Html Report will be added into target directory.

## Usage
- Driver: You can setup browser in LoginUIStepdefs class by changing beforeTest method parameter as Chrome, Firefox or IExplorer
- ScreenShot for Failures: After each step, takeScreenShotForFailure method runs and save image file as png format into screenshots package.

## Contribution
Feel free to comment on anything about this project.
