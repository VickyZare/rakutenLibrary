# rakutenLibrary

The library is an implementation of the problem statement given in email.

Here in this library, we have a class named GithubRepositoriesProvider.java, using that we can get the required data from github search rest API.

GithubRepositoriesProvider.java is a singleton class which has two methods that can be used to get the results from API. 

One method is using OkHttp and retrofit for making the API calls and the response json is manually parsed and converted to model class.
While the other method is using the Retrofit, Gson and Observables to make the API calls and parse the response. 

In GithubRepositoriesProvider.java there is one more mothod we have to parse the JSON named as parseJsonToModel which is responsible for converting the JSON string into the Repository Model. 

In ExampleUnitTest.java, i have writed the test cases which tests the json parsing function with a mock object. 

Inside the build folder, we also have the AAR files which can be directly used as dependencies with other projects.
