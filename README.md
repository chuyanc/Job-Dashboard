# Job-board

Hi! Welcome to the job dashboard backend project~ :)

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
- Testing: JUnit5

## Data Source
The data is from from the Bureau of Labor Statistics website on average earnings:

[May 2021 National Occupational Employment and Wage Estimates](https://www.bls.gov/oes/current/oes_nat.htm#00-0000)

View original Excel data source here: https://www.bls.gov/oes/special.requests/oesm21nat.zip

The modified version of Excel file that can be used directly as the backend database 
can be viewed here: [Modified data source](./src/main/resources/job_board_data.csv)


## How to Run
Pull the project to local environment, should be equipped with:
- Java 17
- MySQL

Before running, import the data source CSV file to MySQL first.

Each time running, run MySQL first, and Run:
`JobBoardApplication.java`

Then open [http://localhost:8080](http://localhost:8080), and adding certain path variables to view the API endpoints.

## Functions
There are several API endpoints with certain functions: 

- `/job-board`: To view the main job dashboard
- `/job-board/get-title/{id}`: To view the title of a certain job on the dashboard
- `/job-board/get-ave/{id}`: To view the annual average salary of a certain job on the dashboard
- `/job-board/expect/{id}`: To view the data of the expected annual average salary in 5 years
- `/job-board/add/{job-title}`: Add jobs of the typed title to the dashboard
- `/job-board/delete/{id}`: Remove a job from the dashboard
- `/job-board/clear`: Clear the job dashboard


## Testing
Carry out test on each API endpoint at path `/src/test/java/com/levralabs/Jobboard` 
and run `JobBoardApplicationTests.java`
