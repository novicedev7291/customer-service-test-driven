package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("/customers/{id} should return a customer JSON")

    request {
        url( "/customers/1")
        method GET()
    }

    response {
        status(200)
        headers {
            contentType(applicationJson())
        }
        body([id: 1, "first": "first", "last": "last", "email": "first@email.com"])
    }
}