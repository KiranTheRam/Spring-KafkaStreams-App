# Spring-KafkaStreams-App
Scrapes domains from domainsdb.info that contain a keyword provided by the user. Then sends the info of all available and unavailable domain names it a Kafka topic. Next it filters the list to only have domains which are active, and sends them to a new Kafka topic.

## Important
Because Intellij was acting up when I pushed the project, here is how you pull and use the project:
1. Pull the master, DomainProcessor, and DomainService branches
2. Set them up each in their own projects
3. Run them

## Usage
After running all modules, AND starting up your Kafka cluster, head to localhost:8080/domain/lookup/{seaarchParameter}.
This is how you pass a domain name to the program. For example of you wanted to search domain names including "facebook" you would do:
localhost:8080/domain/lookup/facebook.
Open a consumer to read from both topics. One will have all matching domain names, and the other will only show the actives domains.
