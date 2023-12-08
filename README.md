# Voting app

--- 

This project has been created to have simple domain to acquire knowledge about programming.
The first iteration will represent model that is coupled and anemicFirstly it will be
3 layered architecture with anemic entitiesNext we will see, for sure I would like to make 
it cleaner.

---

## Functionalities:

### Handling questionnaire

_Creating:_
- every user can create questionnaire
- questionnaire can have maximum 10 questions
- user can have only 10 draft questionnaires
- all questions are mandatory

_Editing:_
- questionnaire can have maximum 10 questions
- only owner user can edit questionnaire
- cannot edit published questionnaire
- cannot edit archived questionnaire

_Deleting:_
- only owner user can delete questionnaire
- cannot delete published questionnaire
- cannot delete archived questionnaire

_Publishing:_
- can only have 3 published questionnaires
- min expiry date is 4 hours from now
- max expiry date is 3 weeks from now
- has to have assigned group of voters

_Unpublishing:_
- can unpublish if no-one voted
- can unpublish until 30 minutes has passed after being published

### Creating voters group

- user can be owner of maximum 3 groups
- user can be assigned to 5 groups
- can delete group:
  - permanently if there is no published questionnaires assign to this group
  - soft delete if there is any published questionnaire
- group has to have at least 3 users
- group can contains maximum 50 users
- can add user to present group, but:
  - he can be active voter in questionnaires that have at least 15 minut till the end
- when adding user to group the invitation will be sent that must be accepted by user

### Voting

- user can vote only if:
  - questionnaire is in published state
  - is assigned to group that owns questionnaire
- user has to answer all questions

### Other staff

- creating reports
- analytics

---

## Dictionaries

### 3 layers arch:
- published questionnaire == users can vote (flag `readyToVote` is true and `votingExpiryDateTime` is present and not expired)
- draft/unpublished questionnaire == is not present to vote (flag `readyToVote` is false and `votingExpiryDateTime` is null)
- archived questionnaire == hold only to archive (`votingExpiryDateTime` is present and expired)

---

## Topics to learn

- [ ] 3 layers/Spock
- [ ] refactor technics
  - [ ] Integration vs unit tests
  - [ ] TestContainers
- [ ] ports & adapters
  - [ ] third layer without tests to adapters
- [ ] save all form vs save per click/type
- [ ] security (OAtuh, SSO etc.)
- [ ] Event Sourcing
- [ ] microservices
  - [ ] split domains
  - [ ] service discovery
  - [ ] circuit breaker
  - [ ] tentants?
  - [ ] Kubernetes/Docker
- [ ] functional approach
- [ ] Kotlin
- [ ] Kafka
- [ ] Read Model/CQRS
- [ ] Slack notifications
- [ ] JOOQ
- [ ] Micronaut/Quarkus
- [ ] Hotwire
- [ ] Observability
- [ ] Profiling
- [ ] Documentation
- [ ] SSL, CORS itp.
- [ ] Bitemporal event
- [ ] jMeter - fake movement on app
