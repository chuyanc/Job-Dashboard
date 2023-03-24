# Job-board


## Introduction
This is the backend of a job dashboard that allows users to select jobs for which they would like to see employment wage data.

## Tech Stacks
- Java 17
- SpringBoot 3.0.4
- MySQL 8.0

## Development Environment
- Operating System: Windows
- Build Tool: Maven
- IDE: IntellJ IDEA
- Version Control: Git
- JDK: 17

##Data Source
[May 2021 National Occupational Employment and Wage Estimates](https://www.bls.gov/oes/current/oes_nat.htm#00-0000)

[Excel file here](https://www.bls.gov/oes/special.requests/oesm21nat.zip)


## How to Run
Pull the project to local environment, should be equipped with:
- Java 17
- MySQL

Before running, import the data source CSV file to MySQL first.
Each time running, run MySQL first, and Run:
- JobBoardApplication.java

Then open [http://localhost:8080](http://localhost:8080), and adding certain path variables to view the API endpoints.

## Testing
