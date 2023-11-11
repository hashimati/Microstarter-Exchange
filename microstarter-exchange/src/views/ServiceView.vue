<template>

  <h1>Service View</h1>

    <div>
        <form class="row g-3">
            <div class="col-md-6">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" v-model="s.name"/>
            </div>

            <div class="col-md-6">
                <label for="alias" class="form-label">Alias</label>
                <input type="text" class="form-control" id="alias" v-model="s.alias"/>
            </div>
            <div class="col-md-6">
                <label for="provider" class="form-label">provider</label>
                <input type="text" class="form-control" id="provider" v-model="s.provider"/>
            </div>
            <div class="col-md-6">
                <label for="rooturl" class="form-label">Root URL</label>
                <input type="text" class="form-control" id="rooturl" v-model="s.rootUrl"/>
            </div>

            <div class="col-md-6">
                <label for="documentation" class="form-label">Documentation</label>
                <input type="text" class="form-control" id="documentation" v-model="s.documentation"/>
            </div>

            <!--  Security as drop down list      -->
            <div class="col-md-6">
                <label for="security" class="form-label">Security</label>
                <select type="text" rows="6" class="form-control" id="security" v-model="s.securityType">
                    <option value="NONE">None</option>
                    <option value="BASIC">Basic</option>
                    <option value="JWT">JWT</option>
                    <option value="API_KEY">Token</option>

                </select>
            </div>
            <!-- if security type is BASIC || JWT show the username and password div-->
            <div class="col-md-6" v-if="s.securityType == 'BASIC' || s.securityType == 'JWT'">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" v-model="s.username"/>
            </div>
            <div class="col-md-6" v-if="s.securityType == 'BASIC' || s.securityType == 'JWT'">
                <label for="password" class="form-label">Password</label>
                <input type="text" class="form-control" id="password" v-model="s.password"/>
            </div>
            <!-- if security type is JWT show the login URL and refresh token URL div-->
            <div class="col-md-6" v-if="s.securityType == 'JWT'">
                <label for="loginUrl" class="form-label">Login URL</label>
                <input type="text" class="form-control" id="loginUrl" v-model="s.loginUrl"/>
            </div>
            <div class="col-md-6" v-if="s.securityType == 'JWT'">
                <label for="refreshTokenUrl" class="form-label">Refresh Token URL</label>
                <input type="text" class="form-control" id="refreshTokenUrl" v-model="s.refreshTokenUrl"/>
            </div>

            <!-- if security type is Token show the token div-->
            <div class="col-md-12" v-if="s.securityType == 'API_KEY'">
                <label for="token" class="form-label">Token</label>
                <textarea type="text" rows="3" class="form-control" id="token" v-model="s.token"></textarea>
            </div>
            <div class="col-md-12">
                <label for="description" class="form-label">Description</label>
                <textarea type="text" rows="6" class="form-control" id="description" v-model="s.description"></textarea>
            </div>
            <div class="col-md-12">
                <label for="status" class="form-label">Status</label>
                <select type="text" rows="6" class="form-control" id="status" v-model="s.status">
                    <option value="ACTIVE">Active</option>
                    <option value="INACTIVE">Inactive</option>
                    <option value="DELETED">Deleted</option>
                </select>
            </div>



        </form>
        <br/>
        <br/>
        <div class = "row g-1">
            <button type="button" class="btn btn-primary" v-on:click="updateService()"  >Update</button>
        </div>
    </div>
<br/>
    <br/>
    <br/>
    <!--    add separator-->
    <hr/>
    <div class="d-flex">
        <div class="p-2 w-100">
            <h2><b>Endpoints</b></h2>
        </div>
        <div class="p-2 flex-shrink-1">
            <button type="button" class="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#endpointModal">Add</button>
            </div>
    </div>
   <!-- add div with border-->
    <div class="border border-primary">
        <div  class="accordion" id="accordionPanelsStayOpenExample">
            <div v-for="(e,index) in endpoints" class="accordion-item">
                <h2 class="accordion-header">

                    <button style="accordion-button-active-color: red" class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#collapse'+index" aria-expanded="true" :aria-controls="'collapse'+index">
                        <span :class="e.type== 'DELETE'? 'badge text-bg-danger':(e.type == 'GET'?'badge text-bg-primary':(e.type=='PUT'?'badge text-bg-warning':(e.type=='POST'?'badge text-bg-success':'badge')))"><h4>{{ e.type }}  </h4></span>
                        <h4>&nbsp&nbsp<b>{{e.url}}</b></h4>
                    </button>
                </h2>
                <div :id="'collapse'+index" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <!--                    <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.-->

                        <div class="mb-3">
                            <h4><b>ID</b></h4>
                            <h4 class="form-control">{{e.id}}</h4>
                        </div>'
                        <div class="mb-3">
                            <h4><b>Path</b></h4>
                            <input type="text" class="form-control" v-model="e.url" />
                        </div>'
                        <div >
                            <h4><b>Method</b></h4>
                            <select id="method" class="form-select" v-model="e.type">
                                <option>GET</option>
                                <option>POST</option>
                                <option>PUT</option>
                                <option>DELETE</option>
                            </select>
                        </div>
                        <hr v-if="e.type == 'POST' || e.type == 'PUT' "/>
                        <div v-if="e.type == 'POST' || e.type == 'PUT' ">
                            <h4><b>Consumes</b></h4>
                            <select v-model="e.mediaType">
                                <option value="text/plain">text/plain</option>
                                <option value="application/json">application/json</option>
                                <option value="application/xml">application/xml</option>
                                <option value="application/x-www-form-urlencoded">application/x-www-form-urlencoded</option>
                                <option value="multipart/form-data">multipart/form-data</option>
                            </select>
                        </div>
                        <hr/>
                        <div>
                            <h4><b>Produces</b></h4>
                            <select v-model="e.contentType">
                                <option value="text/plain">text/plain</option>
                                <option value="application/json">application/json</option>
                                <option value="application/xml">application/xml</option>
                                <option value="application/x-www-form-urlencoded">application/x-www-form-urlencoded</option>
                                <option value="multipart/form-data">multipart/form-data</option>
                            </select>
                        </div>

                        <hr/>
                        <div>

                            <h4><b>headers</b></h4>

                            <div class="row">`
                                <div class="col-md-5">
                                    <input type="text" class="form-control" id="parameters" placeholder="header" v-model="hInput.name"/>
                                </div>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" id="parameters" placeholder="value" v-model="hInput.value"/>

                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-primary" v-on:click=" if(e.headers === undefined) {e.headers = [];} addHeaderG(e.headers,null )">Add</button>
                                </div>
                                <!-- table of the headers -->
                                <div class="col-md-12">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Header</th>
                                            <th scope="col">Value</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="(h,index) in e.headers">
                                            <td>{{h.name}}</td>
                                            <td>{{h.value}}</td>
                                            <td><button type="button" class="btn btn-danger" v-on:click="removeHeaderG(e.headers, index)">Remove</button></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <hr v-if="e.type == 'GET' || e.type == 'DELETE'"/>
                        <div v-if="e.type == 'GET' || e.type == 'DELETE'">
                            <h4><b>Parameters</b></h4>

                            <div class="row">`
                                <div class="col-md-5">
                                    <input type="text" class="form-control" id="parameters" placeholder="parameter" v-model="pInput.name"/>
                                </div>
                                <div class="col-md-5">
                                    <select id="type" class="form-select" v-model="pInput.type">
                                        <option value="String">String</option>
                                        <option value="Number">Number</option>
                                        <option value="Boolean">Boolean</option>
                                    </select>
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-primary" v-on:click="if(e.parameters === undefined) {e.parameters = [];} addParameterG(e.parameters,null )">Add</button>
                                </div>
                                <!-- table of the headers -->
                              <br/>
                              <br/>
                                <div class="col-md-12">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Key</th>
                                            <th scope="col">Value</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="(h,index) in e.parameters">
                                            <td>{{h.name}}</td>
                                            <td>{{h.type}}</td>
                                            <td><button type="button" class="btn btn-danger" v-on:click="removeParameterG(e.parameters, index)">Remove</button></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                      <hr v-if="e.type == 'POST' || e.type == 'PUT'"/>
                      <div v-if="e.type == 'POST' || e.type == 'PUT'">
                        <h4><b>Body</b></h4>

                        <div class="row">`
                          <div class="col-md-5">
                            <input type="text" class="form-control" id="parameters" placeholder="parameter" v-model="pInput.name"/>
                          </div>
                          <div class="col-md-5">
                            <select id="type" class="form-select" v-model="pInput.type">
                              <option value="String">String</option>
                              <option value="Number">Number</option>
                              <option value="Boolean">Boolean</option>
                            </select>
                          </div>
                          <div class="col-md-1">
                            <button type="button" class="btn btn-primary" v-on:click="if(e.parameters === undefined) {e.parameters = [];} addParameterG(e.parameters,null )">Add</button>
                          </div>
                          <!-- table of the headers -->
                          <br/>
                          <br/>
                          <div class="col-md-12">
                            <table class="table">
                              <thead>
                              <tr>
                                <th scope="col">Key</th>
                                <th scope="col">Value</th>
                                <th scope="col">Action</th>
                              </tr>
                              </thead>
                              <tbody>
                              <tr v-for="(h,index) in e.parameters">
                                <td>{{h.name}}</td>
                                <td>{{h.type}}</td>
                                <td><button type="button" class="btn btn-danger" v-on:click="removeParameterG(e.parameters, index)">Remove</button></td>
                              </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                      </div>
<hr/>
                        <div class="mb-3">
                            <h4><b>Description</b></h4>
                            <textarea class="form-control" v-model="e.description" rows="3"></textarea>
                        </div>

                        <hr/>
                        <div class="h-100 d-flex align-items-center justify-content-center">
                            <div >
                                <button type="button" class="btn btn-warning" v-on:click="updateEndpoint(e)" >Update</button>

                            </div>
                            <!-- add space -->
                            &nbsp&nbsp
                            <div>
                                <button type="button" class="btn btn-danger"
                                        v-on:click="removeEndpoint(e.id)">Delete</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>





    <div class="modal fade" id="endpointModal" tabindex="-1" aria-labelledby="EndpointModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h5 class="modal-title" id="EndpointModalLabel">Add Endpoint</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form class="row g-3">
                        <div class="col-md-12">
                            <label for="name" class="form-label">URL</label>
                            <input type="text" class="form-control" id="name" v-model="ep.url"/>
                        </div>

                        <!-- description -->
                        <div class="col-md-12">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control" id="description" v-model="ep.description"/>
                        </div>
                        <!-- method -->
                        <div class="col-md-6">
                            <label for="method" class="form-label">Method</label>
                            <select id="method" class="form-select" v-model="ep.type">
                                <option selected>Choose...</option>
                                <option>GET</option>
                                <option>POST</option>
                                <option>PUT</option>
                                <option>DELETE</option>
                            </select>
                        </div>
                        <!-- content type -->
                        <div class="col-md-6">
                            <label for="contentType" class="form-label">Produces</label>
                            <select id="contentType" class="form-select" v-model="ep.contentType">
                                <option>text/plain</option>
                                <option>application/json</option>
                                <option>application/xml</option>
                            </select>
                        </div>
                        <!-- if method type is POST or Put, then specify the consume media type -->
                        <div v-if="ep.type == 'POST'" class="col-md-12">
                            <label for="mediaType" class="form-label">Consumes</label>
                            <select id="mediaType" class="form-select" v-model="ep.mediaType">
                                <option>plaiuion/xml</option>
                                <option>multipart/form-data</option>
                            </select>
                        </div>

                        <!-- headers  as tags-->
                        <div class="col-md-12">
                            <label for="headers" class="form-label">Headers</label>
                            <div class="row">`
                            <div class="col-md-5">
                                <input type="text" class="form-control" id="headers" placeholder="Key" v-model="hInput.name"/>
                            </div>
                            <div class="col-md-5">
                                <input type="text" class="form-control" id="headers" placeholder="Value" v-model="hInput.value"/>
                            </div>
                            <div class="col-md-1">
                                <button type="button" class="btn btn-primary" v-on:click="addHeader()">Add</button>
                            </div>
                            <!-- table of the headers -->
                                <div class="col-md-12">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">Key</th>
                                        <th scope="col">Value</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-for="(h,index) in headers">
                                        <td>{{h.name}}</td>
                                        <td>{{h.value}}</td>
                                        <td><button type="button" class="btn btn-danger" v-on:click="removeHeader(index)">Remove</button></td>
                                    </tr>
                                    </tbody>
                                </table>
                                    </div>
                        </div>
                    </div>



                        <div  class="col-md-12">
                            <label v-if="ep.type == 'GET' || ep.type == 'DELETE' " for="parameters" class="form-label">Parameters</label>
                            <label v-if="ep.type == 'POST' || ep.type == 'PUT'" for="parameters" class="form-label">Body</label>

                            <div class="row">`
                                <div class="col-md-5">
                                    <input type="text" class="form-control" id="parameters" placeholder="parameter" v-model="pInput.name"/>
                                </div>
                                <div class="col-md-5">
                                    <select id="type" class="form-select" v-model="pInput.type">
                                        <option value="String">String</option>
                                        <option value="Number">Number</option>
                                        <option value="Boolean">Boolean</option>
                                    </select>
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-primary" v-on:click="addParameter()">Add</button>
                                </div>
                                <!-- table of the headers -->
                                <div class="col-md-12">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Key</th>
                                            <th scope="col">Value</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="(h,index) in parameters">
                                            <td>{{h.name}}</td>
                                            <td>{{h.type}}</td>
                                            <td><button type="button" class="btn btn-danger" v-on:click="removeParameter(index)">Remove</button></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>





                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary"  data-bs-dismiss="modal"
                            v-on:click="addEndpoint()">Save</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import WSClient  from "@/assets/clients/WSClient.js";

import EndpointClient from "@/assets/clients/EndpointClient.js";
export default {
    data(){
        return {
            s:{}, //service
            ep:{}, //input endpoint
            endpoints:[], //endpoints
            headers:[], //headers
            hInput:{}, //input header
            parameters:[], //parameters
            pInput:{}, //input parameter

        }
    },
    methods: {


        addEndpoint(){
            console.log("addEndpoint");
            console.log(this.ep);
            // if(this.ep.url === undefined || this.ep.type === undefined){
            //     return;
            // }
            this.ep.headers = this.headers;
            this.ep.parameters = this.parameters;
            this.ep.service = this.s.alias;
            EndpointClient.create(this.ep, r=>{
                console.log(r.data);
                this.ep = {};
                this.headers = [];
                this.parameters = [];
                this.findAllEndpoints();
            });
        },
        addHeader(){
            console.log("addHeader");
            console.log(this.hInput);

            if(this.hInput.value === undefined || this.hInput.name === undefined){
                return;
            }
            this.headers.push(this.hInput);
            this.hInput = {};
        },
        addHeaderG(list){

            console.log("addHeader");
            console.log(this.hInput);

            if(this.hInput.value === undefined || this.hInput.name === undefined){
                return;
            }
            list.push(this.hInput);
            this.hInput = {};
        },
        updateService(){
            console.log("updateService");
            console.log(this.s);
            WSClient.update(this.s.id, this.s, null);
        },
        updateEndpoint(endPoint){
            console.log("updateEndpoint");
            console.log(this.ep);
            EndpointClient.update(endPoint.id, endPoint, null);
        },
removeEndpoint(id){
            console.log("removeEndpoint");
            console.log(id);
            EndpointClient.delete(id, r=>{
                console.log(r.data);
                this.findAllEndpoints();
            });
        },
        findAllEndpoints(alias){
            console.log("findAllEndpoints");
            EndpointClient.findByWebService(alias,r=>{
                console.log(r.data);
                this.endpoints = r.data;
            });
        },

        findByAlias(id){
            console.log("findById");
            console.log(id);
          WSClient.findById(id, r=>{
              console.log(r.data.description);
              this.s = r.data;
          })
        },
        saveService(){
            console.log("saveService");
            console.log(this.s);
            WSClient.create(this.s, r=>{
                console.log(r.data);
                this.s = r.data;
            });
        },
        removeHeader(index){
            console.log("removeHeader");
            console.log(index);
            this.headers.splice(index,1);
        },
        removeHeaderG(list, index){
            console.log("removeHeader");
            console.log(index);
            list.splice(index,1);
        },
        addParameter(){
            console.log("addParameter");
            console.log(this.pInput);

            if(this.pInput.type === undefined || this.pInput.name === undefined){
                return;
            }
            this.parameters.push(this.pInput);
            this.pInput = {};
        },
        addParameterG( list, input){
            console.log("addParameterssss");
            if(this.pInput.type === undefined || this.pInput.name === undefined){
                console.log("Failed to add parameters")
                return;
            }
            list.push(this.pInput);
            this.pInput = {};



        },
        removeParameterG(list, index){
            console.log("removeParameter");
            console.log(index);
            list.splice(index,1);
        },
    },
    created() {
        console.log("created");
        console.log(this.$route.params.id);
        this.findByAlias(this.$route.params.id);
        this.findAllEndpoints(this.$route.params.id);
    }
}
</script>