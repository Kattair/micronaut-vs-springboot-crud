# micronaut-vs-springboot-crud
Repository with simple CRUD application implemented in both Micronaut and Spring Boot frameworks for comparison.

# Current status of the projects

| SpringBoot | Micronaut | Goal                                                         |
|------------|-----------|--------------------------------------------------------------|
| ✔️         | ✔️        | Bean creation and injection                                  |
| ✔️         | ✔️        | JPA communication with DB                                    |
| ✔️         | ✔️        | REST Controller                                              |
| ✔️         | ✔️        | Integration tests                                            |
| ❌          | ✔️        | Another set of Integration tests using declarative webclient |
| ✔️         | ✔️        | Custom configuration properties with configuration classes   |
| ✔️         | ✔️        | Controller dependent on "dev" environment/profile            |

# Running the apps with the PROD application file
Both apps have separate `application-prod` files that by default connect to the database spawned by the `docker_compose.yml` file in the root folder of the project.

To make a Micronaut app use the application-prod file we have to run the app with the `prod` environment (see [Micronaut Environment docs](https://docs.micronaut.io/latest/guide/)).\
That is accomplished by setting the `MICRONAUT_ENVIRONMENTS` environment variable to `prod`. Setting the system property `micronaut.environments` to `prod` should also work according to docs, but I didn't manage to make it run.

To achieve the same with a SpringBoot app we have two options. Either run it with `.\gradlew bootRun --args='--spring.profiles.active=prod'` from the spring_crud_demo folder or set the`spring_profiles_active` environment variable to `prod`.