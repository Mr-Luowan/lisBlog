<!-- 博客详情页 -->
<template>
    <div>
        <div class="cover_c">
            <img class="cover" :src="currentArticle.cover" alt="cover">
        </div>
        <div class="content_c">
            <div class="content" id="preview"></div>
        </div>
    </div>
</template>

<script>
    import Vditor from 'vditor'
    import 'vditor/dist/index.css'
    export default {
        props: ['blogId'],
        data() {
            return {
                currentArticle: {},
            }
        },
        methods: {
            getArticleDetail(articleID) {
                const _this = this;
                this.$axios.get('/api/article/' + articleID)
                .then((res) => {
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
        },
        mounted() {
            this.getArticleDetail(this.blogId);
        }
    }
</script>

<style>
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