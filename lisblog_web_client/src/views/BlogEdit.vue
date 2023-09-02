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
                        <span>上传封面</span>
                        
                        <el-input class="add-cover-btn" type="file" accept="image/gif, image/jpeg, image/gif, image/png, image/bmp, image/webp"></el-input>
                    </div>
                    
                    <el-button type="primary" @click="submitForm">提交</el-button>
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
                    content: ''
                }
            }
        },
        methods: {
            jumpHomePage() {
                this.$router.push("/blogs")
            },
            addCover() {

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
    float: right;
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
    padding: 0;
    color: white;
    text-align: center;
  }
  .add-cover-btn {
    width: 100px;
    height: 32px;
    opacity: 0;
  }
  
</style>