# Items Checkout Application - JobCheckOut

JobCheckOut is a checkout application project with MVVM Design Structure by using RXJava2 with LiveData and Repository Patterns. It contains the job list which allows user to post or edit their job advertisement.

From this example, we use the APIs provide by [Mock Json](https://sunnytse0326.github.io/MockJson/jobs/requirement.json), which is the settings about the special price rules for each customer and it will return the following result:
```
{
    "jobType" : [
        {
            "id": "classic",
            "price": 269.99
        },
        {
            "id": "standout",
            "price": 322.99
        },
        {
            "id": "premium",
            "price": 394.99 
        }
    ],
    "rules" : [
        {
            "customer": "UNILEVER",
            "oneFreeForDeal": {
                "numOfDeal": 2,
                "id": "classic"
            }
        },
        {
            "customer": "APPLE",
            "discount": [
                {
                    "id": "standout",
                    "numOfDeal": 1,
                    "price": 299.99
                }
            ]
        },
        {
            "customer": "NIKE",
            "discount": [
                {
                    "id": "premium",
                    "numOfDeal": 4,
                    "price": 379.99
                }
            ]
        },
        {
            "customer": "FORD",
            "oneFreeForDeal": {
                "numOfDeal": 2,
                "id": "classic"
            },
            "discount": [
                {
                    "id": "standard",
                    "numOfDeal": 1,
                    "price": 309.99
                },
                {
                    "id": "premium",
                    "numOfDeal": 3,
                    "price": 389.99
                }
            ]
        }
    ]
}
```

# Project Architechure
In the project, we used MVVM design pattern with Google Android Architecture Components (AAC). The application used RXJava2 to perform data binding. With Android Architecture Lifecycle, it observes and monitor the network data fetched from Fuel Library.

The following diagram shows the flow how it works in the project:

<p float="left">
  <img src="https://github.com/sunnytse0326/JobAdCheckout/blob/master/screenshot/structure.png" width="350" height="550">
</p>


# Implementation
User need to choose the which type of customers they belong to for the login part of this application. After that user could add, update or delete their job ads in the list and the dedicate price will be shown at the bottom. 
<p float="left">
  <img src="https://github.com/sunnytse0326/JobAdCheckout/blob/master/screenshot/screenshot1.png" width="250" height="400">
  <img src="https://github.com/sunnytse0326/JobAdCheckout/blob/master/screenshot/screenshot2.png" width="250" height="400">
  <img src="https://github.com/sunnytse0326/JobAdCheckout/blob/master/screenshot/screenshot3.png" width="250" height="400">
</p>


# TDD test case
In this example, we added several Test Driven Development (TDD) test cases for some simple checking of UI and view model. 

# Todo list
- BDD test cases
We would like to demonstrate how to implement the black-box testing with the use of steps file inside the project. 


# Library used:
Anko (Layout Design)
Fuel (Network Library)
RxJava2 (Data binding)



