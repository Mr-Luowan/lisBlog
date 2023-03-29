<!-- 博客详情页 -->
<template>
    <div>
        <div class="cover_c">
            <img class="cover" :src="currentArticle.cover" alt="cover">
        </div>
        <div>
            <span>{{ currentArticle.content }}</span>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['blogId'],
        data() {
            return {
                currentArticle: {},
                md: "__hello__"
            }
        },
        methods: {
            getArticleDetail(articleID) {
                const _this = this;
                this.$axios.get('/api/article/' + articleID)
                .then((res) => {
                    _this.currentArticle = res.data.data
                    console.log(_this.currentArticle)
                })
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
</style>