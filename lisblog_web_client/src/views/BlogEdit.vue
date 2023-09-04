<!-- 编辑博客 -->

<template>
    <div class="bg">
        <div class="header">            
            <el-row>
                <el-col :span="16">
                    <el-input class="title" v-model='edit_article.title' placeholder="请输入文章标题"></el-input>
                </el-col>
                <el-col :span="4" :offset="4">
                    <div class="add-cover-div">
                        <span>
                            <label for="fileInput" class="input-button" title="上传封面">上传封面</label>
                            <input id="fileInput" type="file" style="display: none;" @change="addCover()">
                        </span>
                    </div>                    
                    <el-button type="primary" class="submit_btn" @click="submitForm">提交</el-button>
                </el-col>
            </el-row>
        </div>
        <el-main>
            <div id="vditor"></div>
        </el-main>            
    </div>
</template>

<script>
    import Vditor from 'vditor'
    import 'vditor/dist/index.css'
    import { ElMessage } from 'element-plus'
    import http from '../axiosdir'
    export default {
        data() {
            return {
                contentEditor: {},
                edit_article: {
                    title: '',
                    content: '',
                    cover: ''
                }
            }
        },
        methods: {
            jumpHomePage() {
                this.$router.push("/blogs")
            },
            addCover() {
                const _this = this
                var fileInput = document.getElementById("fileInput");
                let img = fileInput.files[0];
                console.log("选择的图片 ", img)
                const formData = new FormData();
                formData.append("file", img);
                http(
                    {
                        url:'/api/images/upload',
                        data: formData,
                        headers: {
                            isToken: true
                        },
                        method: "post"
                    }
                ).then((res) => {
                    const imgUrl = res.data.data;
                    _this.edit_article.cover = imgUrl;
                    console.log("上传图片结果 ", imgUrl)
                    ElMessage.success("上传成功")
                }).catch((err) => {
                    console.error("上传出错");
                });
            },
            submitForm() {
                let content = this.contentEditor.getValue()
                if (!this.edit_article.title || this.edit_article.title == 'undefined')
                {                
                    ElMessage.warning("标题不能为空");
                    return;
                }
                if (!content || content == 'undefined' || content.length < 2)
                {
                    ElMessage.warning("内容不能为空");
                    return;
                }
                    console.log("文章内容= ", content)
                    console.log("文章内容长度= ", content.length)
                this.edit_article.content = content;
                //todo
                this.edit_article.userId = "1"
                this.edit_article.categoryId = "1"
                console.log("提交文章 ",this.edit_article)
                http({
                    url:'/api/article/add',
                    headers: {
                        isToken: true
                    },
                    method: 'post',
                    data:  this.edit_article,

                })
                .then((res) => {
                    this.$router.push("/blogs")
                    ElMessage.success("提交成功")
                    setTimeout(this.jumpHomePage, 1000);
                })
            }
        },
        mounted() {
            this.contentEditor = new Vditor('vditor', {
                height: 800,
                placeholder: '文章正文区域',
                theme: 'classic',
                counter: {
                    enable: true,
                    type: 'markdown'
                },
                preview: {
                    delay: 0,
                    hljs: {
                        style: 'monokai',
                        lineNumber: true
                    }
                },
                tab: '\t',
                typewriterMode: true,
                toolbarConfig: {
                    pin: true
                },
                cache: {
                    enable: false
                },
                mode: 'wysiwyg',
                toolbar: [
                        'headings',
                        'bold',
                        'italic',
                        'strike',
                        'link',
                        '|',
                        'list',
                        'ordered-list',
                        'check',
                        'outdent',
                        'indent',
                        '|',
                        'quote',
                        'line',
                        'code',
                        'inline-code',
                        'insert-before',
                        'insert-after',
                        '|',
                        'upload',
                        'table',
                        '|',
                        'undo',
                        'redo',
                        '|',
                        'fullscreen',
                        'edit-mode',
                        {
                            name: 'more',
                            toolbar: [
                                'both',
                                'code-theme',
                                'content-theme',
                                'export',
                                'outline',
                                'preview',
                                'devtools',
                                'info',
                                'help'
                            ]
                        }
                    ]
            })
        }
    }
</script>

<style>
  .bg {
    padding: 0px 50px;
  }
  .header {
    margin-top: 20px;
    padding: 0 70px;
  }
  .submit_btn {
    margin-left: 10px;
    margin-top: -3px;
  }
  #vditor {
    margin-top: 60px;
  }
  .add-cover-div {
    width: 100px;
    height: 32px;
    background-color: var(--main_color);
    border-radius: 5px;
    display: inline-block;
    color: white;
    text-align: center;
  }
  .input-button {
    line-height: 32px;
  }
  .add-cover-btn {
    width: 100px;
    height: 32px;
    opacity: 0;
  }
  
</style>