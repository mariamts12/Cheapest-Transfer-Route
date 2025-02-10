curl -X POST http://localhost:8080/api/cheapest-transfer-route \
-H "Content-Type: application/json" \
-d '{
      "maxWeight": 12,
      "availableTransfers": [
        {
          "weight": 10,
          "cost": 20
        },
        {
          "weight": 10,
          "cost": 15
        },
        {
          "weight": 12,
          "cost": 25
        }
      ]
    }'

echo ""

curl -X POST http://localhost:8080/api/cheapest-transfer-route \
-H "Content-Type: application/json" \
-d '{
      "maxWeight": 50,
      "availableTransfers": [
        {
          "weight": 10,
          "cost": 20
        },
        {
          "weight": 12,
          "cost": 25
        }
      ]
    }'