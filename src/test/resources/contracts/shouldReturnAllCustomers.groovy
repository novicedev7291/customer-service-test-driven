package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        url("/customers")
        method GET()
    }
    response {
        status(200)
        headers {
            contentType(applicationJson())
        }
        body(
                [
                        [id: 1, "first": "first", "last": "last", "email": "first@email.com"],
                        [id: 2, "first": "second", "last": "last", "email": "second@email.com"]
                ]
        )
    }
}
