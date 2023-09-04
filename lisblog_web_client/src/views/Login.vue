<!-- 登录页面 -->

<template>
    <div class="bg">
        <div class="info_card">
            <div>
                <router-link to="/blogs">
                        <img class="logo" 
                            src="http://localhost:8080/upload/1/20230406/cat-g35d22bf2a_640.jpg" alt="logo">
                </router-link>
            </div>
            <div class="login_form_c">
                <el-form class="login_form" :model="form" status-icon :rules="rules" ref="login_form" label-width="65px">
                    <el-form-item class="t-el-input" label="用户名" prop="userName">
                        <el-input type="text" maxlength="12" v-model="form.userName"></el-input>
                    </el-form-item>
                    <el-form-item class="t-el-input" label="密码" prop="password">
                        <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('login_form')">
                            登录
                        </el-button>
                        
                        <el-button type="primary" @click="resetForm('login_form')">
                            重置
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        
    </div>
</template>

<script>
import { h } from 'vue'
import { ElMessage } from 'element-plus'

import store from "../store";
export default {
    name: 'Login',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value == '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        return {
            imageUrl: '',
            dialogVisible: false,
            headerObj: {  
                token: store.getters.getToken
            },
            form: {
                userName: '',
                password: ''
            },
            rules: {
                password: [
                    {required: true, validator: validatePass, trigger: 'blur'}
                ],
                userName: [
                    {required: true, message: '请输入用户名', trigger: 'blur'},
                    {min:3, max: 12, message: '长度在3到12个字符', trigger: 'blur'}
                ]
            }
        };
    },
    methods: {
        submitForm(form_name) {
            const _this = this
            this.$refs[form_name].validate((valid) => {
                if(valid) {
                    this.$axios.post("/api/user/login", this.form)
                    .then((res) => {
                        const token = res.data.data
                        console.log("返回结果 用户token ", res.data.data)
                        _this.$store.commit('SET_TOKEN', token)
                        _this.$store.commit('SET_USERINFO', res.data)
                        _this.$router.push('/blogs')
                    }) 
                } else {
                    console.log('error submit');
                    return false;
                }
            });
        },
        resetForm(form_name) {
        },
    
    },
    mounted() {
        this.$notify({
            title: "欢迎",
            message: "欢迎来到我的博客",
            duration: 1500
        });
    }
}
</script>

<style scoped>
    .info_card {
        height: 400px;
        width: 759px;
        border-radius: 16px;
        padding: 20px;
        background-color: white;
        margin-top: 150px;
    }
    .login_form_c {
        margin-top: 20px;
    }
    .logo {
        height: 100px;
        width: 100px;
        border-radius: 50%;
        margin-top: 10px;
        margin-left: 110px;
    }
    .t-el-input {
        width: 300px;
    }
    .bg {
        width: 100%;
        height: 100%;
        background-image: url('/sunset.jpg');
        display: flex;
        justify-content: center;
    }
</style>

