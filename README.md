# Cheapest Transfer Route Optimizer

A Spring Boot application that finds the optimal combination of transfers for a logistics company while ensuring the total package weight stays within a given limit. The application uses dynamic programming (0/1 Knapsack algorithm) to maximize the total cost while respecting weight constraints.

## Prerequisites

- Java 17 or higher
- Gradle
- IDE (recommended: IntelliJ IDEA or Eclipse)

## Building the Application

### Using Gradle
```bash
./gradlew clean build
```

## Running the Application

### Running the JAR file
```bash
java -jar build/libs/cheapest-transfer-route-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Documentation

### Find The Cheapest Transfer Route

Calculates the optimal combination of transfers while respecting the weight limit.

**Endpoint:** `POST /api/cheapest-transfer-route`

**Request Format:**
```json
{
    "maxWeight": 15,
    "availableTransfers": [
        {
            "weight": 5,
            "cost": 10
        },
        {
            "weight": 10,
            "cost": 20
        },
        {
            "weight": 3,
            "cost": 5
        },
        {
            "weight": 8,
            "cost": 15
        }
    ]
}
```

**Response Format:**
```json
{
    "selectedTransfers": [
        {
            "weight": 5,
            "cost": 10
        },
        {
            "weight": 10,
            "cost": 20
        }
    ],
    "totalCost": 30,
    "totalWeight": 15
}
```

### Example CURL Request
```bash
curl -X POST http://localhost:8080/api/cheapest-transfer-route \
-H "Content-Type: application/json" \
-d '{
    "maxWeight": 15,
    "availableTransfers": [
        {
            "weight": 5,
            "cost": 10
        },
        {
            "weight": 10,
            "cost": 20
        },
        {
            "weight": 3,
            "cost": 5
        },
        {
            "weight": 8,
            "cost": 15
        }
    ]
}'
```

## Implementation Details

- The application uses the 0/1 Knapsack algorithm to find the optimal combination of transfers
- Input validation ensures that the request data is valid
- Unit tests and integration tests are included
- The solution follows Spring Boot best practices with proper separation of concerns (Controller, Service, Model layers)

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Successful operation
- 400: Invalid request (e.g., negative weights, null values)
- 500: Server error

## Testing

Run the tests using:

### Gradle
```bash
./gradlew test
```

## Constraints

1. If no valid combination of transfers is possible (e.g., all transfers exceed the weight limit), the API returns an empty list of selected transfers and totalCost = 0
2. Transfers cannot be split (must take the full weight of each transfer if selected)

## Technical Decisions

1. Used Spring Boot framework for rapid development and industry-standard practices
2. Implemented using Java for robust type safety and extensive library support
3. Dynamic programming solution for optimal performance
4. REST API design for easy integration with any client
5. Comprehensive test coverage for reliability
