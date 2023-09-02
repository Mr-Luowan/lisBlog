<!-- 博客详情页 -->
<template>
    <div class="post">
        <div class="cover_c">
            <img class="cover" :src="currentArticle.cover" alt="cover">
        </div>
        <div class="content_c">
            <div class="content" id="preview"></div>
        </div>
        <div v-if="btnFlag" class="go-top" @click="backTop">
            
        </div>
    </div>        
</template>

<script>
    import Vditor from 'vditor'
    import http from '../axiosdir'
    import 'vditor/dist/index.css'
    export default {
        props: ['blogId'],
        data() {
            return {
                btnFlag: false,
                scrollTop: 0,
                currentArticle: {},
            }
        },
        methods: {
            getArticleDetail(articleID) {
                const _this = this;
                http(
                    {
                        url:'/api/article/' + articleID,
                        headers: {
                            isToken: true
                        },
                        method: "get"
                    }
                ).then((res) => {
                    _this.currentArticle = res.data.data
                    _this.renderMarkdown(_this.currentArticle.content)
                    console.log(_this.currentArticle)
                })
            },
            renderMarkdown(md) {
                Vditor.preview(document.getElementById("preview"), md, {
                    hljs: { style: "github" },
                });
            },
            backTop() {
                const _this = this;
                let timer = setInterval(()=> {
                    let ispeed = Math.floor(-_this.scrollTop / 5);
                    document.documentElement.scrollTop = document.body.scrollTop = _this.scrollTop + ispeed;
                    if (_this.scrollTop === 0)
                    {
                        clearInterval(timer);
                    }
                }, 16);
            },
            scrollToTop() {
                const _this = this;
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                _this.scrollTop = scrollTop;
                if (_this.scrollTop > 0)
                {
                    _this.btnFlag = true;
                } else {
                    _this.btnFlag = false;
                }
            }
        },
        mounted() {
            window.addEventListener('scroll', this.scrollToTop);
            this.getArticleDetail(this.blogId);
        },
        deactivated() {
            window.removeEventListener('scroll', this.scrollToTop)
        }
    }
</script>

<style>
    .post {
        box-shadow: 0 3px 8px 6px rgba(7,17,27,0.05);
    }
    .go-top {
        width: 60px;
        height: 60px;
        background-color: black;
    }
    .cover_c {
        display: flex;
        width: 100vm;
        height: 300px;
        justify-content: center;
        align-content: center;
    }

    .cover {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .content_c {
        display: flex;
        justify-content: center;
    }
    
    .content {
        width: 80%;
    }
</style>