

import axios from 'axios';
let WSClient ={

    findAll(fn) {
        axios.get('http://localhost:8787/api/v1/webService/findAll'
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    },


    findById(id, fn) {
        axios
            .get('http://localhost:8787/api/v1/webService/get?id=' + id
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

        axios
            .post('http://localhost:8787/api/v1/webService/save', context)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, context, fn) {
        axios
            .put('http://localhost:8787/api/v1/webService/update', context
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
            .delete('http://localhost:8787/api/v1/webService/delete/' + id
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

export default WSClient;