

import axios from 'axios';
let EndpointClient ={

    findAll(fn) {
        axios.get('http://localhost:8787/api/v1/endpoint/findAll'
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    },
    findByWebService(service,fn){

        axios.get('http://localhost:8787/api/v1/endpoint/getByService?service='+service
        ).then(response => fn(response))
            .catch(error => console.log(error))
    },

    findById(id, fn) {
        axios
            .get('http://localhost:8787/api/v1/endpoint/get?id=' + id
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(context, fn) {


        console.log("I'm here");
        axios
            .post('http://localhost:8787/api/v1/endpoint/save', context)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, context, fn) {
        axios
            .put('http://localhost:8787/api/v1/endpoint/update', context
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    delete(id, fn) {
        axios
            .delete('http://localhost:8787/api/v1/endpoint/delete/' + id
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    }
}

export default EndpointClient;