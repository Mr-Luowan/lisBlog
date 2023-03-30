<!-- 博客列表 -->

<template>
    <div>
        <Header></Header>
    </div>
    <div class="">
        <div class="">
            <div class="article_list_container" v-for="(article, index) in articles" :key="index">
                <div class="article_item">
                    <span @click="goDetail(article.id)">{{ article.title }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '../components/Header.vue'
    export default {
        name: 'Blogs',
        components: {Header},
        data() {
            return {
                articles: {},
                currentPage: 1,
                total: 0,
                pageSize: 5
            }
        },
        methods: {
            page(currentPage) {
                const _this = this
                this.$axios.get('/api/article/articles?pageNum=' + currentPage + '&pageSize=3')
                .then((res) => {
                    _this.articles = res.data.data
                    for(var index in _this.articles) {
                        console.log('单篇文章结果  ',  _this.articles[index])
                    }
                })
            },
            goDetail(blogId) {
                this.$router.push({ name: 'BlogDetail', params: {'blogId': blogId} })
            }
        },
        mounted () {
            this.page(1);
        }

    }

</script>

<style>
    .article_list_container {
        /* 上右下左 */
        /* margin: 50px, 10%, 0px,  10%; */
        display: flex;
        justify-content:center;
    }
    .article_item {
        width: 1080px;
        height: 310px;
        margin-top: 35px;
        background-color: rgb(193, 29, 20);
        border-radius: 10px;
    }
</style>