<!-- 登录页面 -->

<template>
    <div class="all">
        <el-container>
            <el-header>
                <router-link to="/blogs">
                    <img class="logo" 
                        src="http://localhost:8080/upload/1/20230406/cat-g35d22bf2a_640.jpg" alt="logo">
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

            <el-upload action="/api/images/upload" list-type="picture-card" name="file"
              :headers="headerObj"
              accept="image/jpeg,image/png,image/jpg" :on-preview="handlePictureCardPreview" :on-remove="handleRemove"
              :on-success="handleimg">
              <el-icon>
                <Plus />
              </el-icon>
              <span>只能上传jpg/png/jpeg文件，且单个不超过2M</span>
            </el-upload>
            <el-dialog v-model="dialogVisible">
              <img w-full :src="imageUrl" alt="Preview Image" />
            </el-dialog>
        </el-main>
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
                        console.log("返回结果 ", res)
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
            // this.$refs[form_name].resetFields();
        },
        handleRemove(img, fileList) {
            console.log("移除图片  handleRemove")
            // console.log(img, fileList);
        },
        handlePictureCardPreview(img) {//这里需要注意 用的img并不是file，因为接口文档给的是img，我们在upload中定义个name=“img”参数就可以了
      // 检查文件类型
            console.log("检查文件类型 handlePictureCardPreview")
            // const isImage = img.raw.type.includes("image");
            // if (!isImage) {
            //     this.$message.error("上传文件类型必须是图片!");
            //     return false
            // }
            // // 检查文件大小
            // if (img.size > (2 * 1024 * 1024)) {
            //     this.$message.error(`上传文件大小不能超过10Mb`);
            //     this.$refs['refUpload'].handleRemove(img);
            //     return false;
            // }
            // // 检查文件数量
            // if (fileList.length > 1) {
            //     this.$message.error(`上传文件最大数量为1`);
            //     this.$refs['refUpload'].handleRemove(img);
            //     return false;
            // }
            // this.ImageUrl = img.url;
            // this.dialogVisible = true;
        },
        handleimg(res, img, fileList) {
            console.log("处理  handleimg")
            console.log(img);
            console.log(fileList);
            // if (res.code === 200) {
            //     this.url = res.data.file
            // } else {
            //     this.$message.error(`图片${img.name}上传失败`)
            // }
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
    .logo {
        height: 100%;
        width: 200px;
        margin-top: 10px;
    }
    .t-el-input {
        width: 600px;
    }
    .all {
    }
</style>

