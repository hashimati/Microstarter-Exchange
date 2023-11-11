<template>


    <div>
        <div class="row g-3">
            <div class="col-sm-12">
                <h3>Services</h3>
            </div>
            <div class="col-sm-10">
                <input
                        type="text"
                        class="form-control"
                        placeholder="search"
                        aria-label="search"
                />
            </div>

            <div class="col-sm-1">
                <button
                        class="btn btn-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#contextModal"
                >
                    Add
                </button>
            </div>
            <div class="col-sm-1">
                <button class="btn btn-primary" v-on:click="findAll()">Load</button>
            </div>
            <div class="col-md-12">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">alias</th>
                        <th scope="col">name</th>
                        <th scope="col">description</th>
                        <th scope="col">status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="x in servicesList">
                        <th scope="row">
                            {{ x.alias }}
                        </th>
                        <th scope="row">
                            {{ x.name }}
                        </th>
                        <th scope="row">
                            {{ x.description }}
                        </th>
                        <th scope="row">
                            {{ x.status }}
                        </th>
                        <th>
                            <button
                                    class="btn-primary btn btn-secondary"
                                v-on:click="showService(x.alias)">
                                Show
                            </button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>



    </div>
    <div class="modal fade" id="contextModal" tabindex="-1" aria-labelledby="employerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h5 class="modal-title" id="exampleModalLabel">Add A Servcie</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
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
                                <option selected value="NONE">None</option>
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
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary"  data-bs-dismiss="modal"
                    v-on:click="addService()">Save</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import WSClient from "@/assets/clients/WSClient.js";
  export default {

      data(){
          return {
              s:{

              },
              servicesList:[
                  {
                      id:1,
                      alias:"s1",
                      name:"service1",
                      description:"service1",
                      status:"verified"

                  },


              ]
          }
      },
      methods:{
          addService(){
              console.log(this.s)
              WSClient.create(this.s, r=>{
                  console.log(r)
                  this.s = r.data;
              });

          },
          findAll(){
              WSClient.findAll(r=>{
                  this.servicesList = r.data;
              });
          },
          showService(id){

              console.log(id)
               this.$router.push({path:'/service/'+id})
          }
      },
      created() {
          this.findAll();
      }


  }
</script>