# Spring Boot 3 Spring Data GraphQL CRUD

A comprehensive demonstration of integrating **Spring Boot 3**, **Spring Data JPA**, **GraphQL**, **HyperSQL Database**, and **Java 17** to build a modern CRUD application with advanced error handling.

## 📚 Learning Resources

### Detailed Tutorials

1. **[Spring Boot 3 Spring Data GraphQL CRUD](https://jarmx.blogspot.com/2023/05/spring-boot-3-spring-data-graphql-crud.html)** - Complete step-by-step tutorial
2. **[GraphQL Error Handling](https://jarmx.blogspot.com/2023/09/graphql-error-handling-with-spring-boot.html)** - Advanced error handling techniques

   
## 🚀 Features

- ✅ **GraphQL API** with queries and mutations
- ✅ **Spring Data JPA** for database operations
- ✅ **HyperSQL (HSQLDB)** in-memory database
- ✅ **Custom GraphQL Error Handling** with proper HTTP status classification
- ✅ **GraphiQL** interactive query interface
- ✅ **Hibernate** with SQL debugging
- ✅ **Lombok** for reduced boilerplate code
- ✅ **Maven** build configuration

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 3.1.0 | Application framework |
| Java | 17 | Programming language |
| GraphQL | Latest | API query language |
| Spring Data JPA | Latest | Data persistence |
| HyperSQL | Latest | In-memory database |
| Lombok | Latest | Code generation |
| Maven | Latest | Build tool |

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **IDE** (IntelliJ IDEA recommended)

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/henry/
│   │   ├── controller/          # GraphQL Controllers
│   │   ├── model/              # JPA Entities
│   │   ├── repository/         # Spring Data Repositories
│   │   ├── resolver/           # GraphQL Resolvers
│   │   └── config/             # Configuration classes
│   └── resources/
│       ├── graphql/            # GraphQL Schema files
│       └── application.yml     # Application configuration
└── test/                       # Test files
```

## ⚙️ Configuration

### Maven Dependencies (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-graphql</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.graphql-java</groupId>
        <artifactId>graphql-java-tools</artifactId>
        <version>5.2.4</version>
    </dependency>
    <dependency>
        <groupId>org.hsqldb</groupId>
        <artifactId>hsqldb</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### Application Configuration (application.yml)

```yaml
spring:
  datasource:
    url: jdbc:hsqldb:mem:testdb
    username: sa
    password:
    driver-class-name: org.hsqldb.jdbc.JDBCDriver
  
  # Hibernate Configuration
  jpa:
    database-platform: org.hibernate.dialect.HSQLDialect
    hibernate.ddl-auto: create-drop
  
  # GraphQL Configuration
  graphql:
    graphiql:
      enabled: true

logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
```

## 📊 GraphQL Schema

```graphql
type Query {
    getAllBooks: [Book]
    getAllAuthors: [Author]
    getBookById(id: ID!): Book
    getAuthorById(id: ID!): Author
}

type Mutation {
    createAuthor(firstName: String!, lastName: String!): Author
    updateAuthor(id: ID!, firstName: String!, lastName: String!): Author
    deleteAuthor(id: ID!): Boolean
    createBook(title: String!, authorId: ID!): Book
    updateBook(id: ID!, title: String!): Book
    deleteBook(id: ID!): Boolean
}

type Book {
    id: ID
    title: String!
    author: Author!
}

type Author {
    id: ID
    firstName: String!
    lastName: String!
    bookRecords: [Book]
}
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/HenryXiloj/Spring-Boot-3-Spring-Data-GraphQL-CRUD.git
cd Spring-Boot-3-Spring-Data-GraphQL-CRUD
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access GraphiQL Interface

Open your browser and navigate to: `http://localhost:8080/graphiql`

## 📝 GraphQL Examples

### Author Operations

#### Create Author
```graphql
mutation {
    createAuthor(firstName: "John", lastName: "Doe") {
        id
        firstName
        lastName
    }
}
```

#### Get All Authors
```graphql
query {
    getAllAuthors {
        id
        firstName
        lastName
        bookRecords {
            id
            title
        }
    }
}
```

#### Update Author
```graphql
mutation {
    updateAuthor(id: 1, firstName: "Jane", lastName: "Smith") {
        id
        firstName
        lastName
    }
}
```

#### Delete Author
```graphql
mutation {
    deleteAuthor(id: 1)
}
```

### Book Operations

#### Create Book
```graphql
mutation {
    createBook(title: "Spring Boot Guide", authorId: 1) {
        id
        title
        author {
            firstName
            lastName
        }
    }
}
```

#### Get All Books
```graphql
query {
    getAllBooks {
        id
        title
        author {
            firstName
            lastName
        }
    }
}
```

#### Update Book
```graphql
mutation {
    updateBook(id: 1, title: "Advanced Spring Boot") {
        id
        title
    }
}
```

#### Delete Book
```graphql
mutation {
    deleteBook(id: 1)
}
```

## 🛡️ Error Handling

This project implements comprehensive GraphQL error handling that returns appropriate error classifications instead of generic internal errors.

### Custom Error Response Format

```json
{
    "errors": [
        {
            "message": "An error occurred while processing your request.",
            "locations": [
                {
                    "line": 2,
                    "column": 3
                }
            ],
            "path": ["getAllAuthors"],
            "extensions": {
                "classification": "BAD_REQUEST"
            }
        }
    ],
    "data": {
        "getAllAuthors": null
    }
}
```

### Supported Error Classifications

- `BAD_REQUEST` (HTTP 400)
- `NOT_FOUND` (HTTP 404)
- `INTERNAL_ERROR` (Default fallback)

## 🏛️ Architecture

### Data Models

- **Author**: Represents book authors with first name, last name, and associated books
- **Book**: Represents books with title and author relationship

### Key Components

- **Controllers**: Handle GraphQL query and mutation mappings
- **Resolvers**: Implement business logic for GraphQL operations
- **Repositories**: Provide data access using Spring Data JPA
- **Exception Handlers**: Custom error handling for GraphQL operations

### Key Concepts Covered

- GraphQL vs REST API differences
- Spring Data JPA entity relationships
- GraphQL schema design
- Custom exception handling
- In-memory database configuration
- GraphiQL query interface usage

## 🙏 Acknowledgments

- Spring Boot team for excellent GraphQL integration
- GraphQL community for comprehensive documentation
- HyperSQL team for the lightweight database solution

