<!-- 登录页面 -->

<template>
    <div class="all">
        <el-container>
            <el-header>
                <router-link to="/blogs">
                    <img class="logo" 
                        src="https://www.markerhub.com/dist/images/logo/markerhub-logo.png" alt="logo">
                </router-link>
            </el-header>
        </el-container>
        <el-main>
            <el-form class="login_form" :model="form" status-icon :rules="rules" ref="login_form" label-width="100px">
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
        </el-main>
    </div>
</template>

<script>
import { h } from 'vue'
import { ElMessage } from 'element-plus'

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
                        console.log("返回结果", res)
                        console.log("Token内容", token)
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
            this.$refs[form_name].resetFields();
        }
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
    .logo {
        height: 60%;
        margin-top: 10px;
    }
    .t-el-input {
        width: 600px;
    }
    .all {
    }
</style>

