# PriceController API

This API provides an endpoint to retrieve price information based on a specific date, product ID, and brand ID. The endpoint allows you to query the price for a given date and product within a specified brand returning the option with the highest priority if more than one is found.

## Endpoint

### Retrieve Price Information

#### GET /prices/query

This endpoint allows you to retrieve price information for a specific date, product ID, and brand ID.

**Request Parameters:**

- date (Required): The date and time for which you want to retrieve the price information. Use the format yyyy-MM-dd'T'HH:mm:ss.
- productId (Required): The unique identifier of the product for which you want to retrieve the price.
- brandId (Required): The unique identifier of the brand associated with the product.

**Example Request:**

GET /prices/query?date=2020-06-14T10:00:00&productId=35455&brandId=1

**Response:**

- 200 OK: The request was successful, and the response contains the price information for the specified parameters.
- 404 Not Found: No valid price was found for the specified parameters.

**Example Response (200 OK):**

{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14 00:00:00",
  "endDate": "2020-12-31 23:59:59",
  "price": 35.50,
  "curr": "EUR"
}

**Example Response (404 Not Found):**

{
  "error": [
    {
      "timestamp": "2023-09-01T14:30:00",
      "code": 404,
      "detail": "No valid price found"
    }
  ]
}

## Usage

To use this API, make a GET request to the /prices/query endpoint with the required query parameters (date, productId, and brandId). The API will return the relevant price information for the specified parameters.

## Error Handling

If the API cannot find a valid price for the given parameters, it will return a 404 Not Found response with an error message indicating that no valid price was found.

## Test Scenarios

The API has been tested with the following scenarios to ensure correct behavior:

- Test 1: Request at 10:00 AM on the day 14 for product 35455, brand 1 (ZARA).
- Test 2: Request at 4:00 PM on the day 14 for product 35455, brand 1 (ZARA).
- Test 3: Request at 9:00 PM on the day 14 for product 35455, brand 1 (ZARA).
- Test 4: Request at 10:00 AM on the day 15 for product 35455, brand 1 (ZARA).
- Test 5: Request at 9:00 PM on the day 16 for product 35455, brand 1 (ZARA).

## Author

- Guillermo Steren
