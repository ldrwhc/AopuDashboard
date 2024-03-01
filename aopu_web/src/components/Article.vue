<template>
  <el-tabs v-model="activeName" style="margin: 0 20px 0 20px"  @tab-click="handleClick">

    <el-tab-pane label="编辑" name="first" >
      <el-form>
        <el-form-item label="标题">
          <el-input v-model:html="addArticle.title"></el-input>
        </el-form-item>
        <el-form-item label="文章内容">
          <rich-text-editor v-model:modelHtml="addArticle.content" v-if="isGetData"></rich-text-editor>
        </el-form-item>
        <el-button style="float: right;width: 200px;" @click="submitArticle">提交</el-button>
      </el-form>
    </el-tab-pane>

    <el-tab-pane label="文章列表" name="second">
      <div v-for="blog in articleList" :key="blog.id">
        <el-card class="box-card" style="margin-bottom: 10px">
          <div slot="header" class="clearfix" >
            <h1 style="float:left; font-size: 30px">{{blog.title}}</h1>
            <el-tag effect="plain" style="float:right; height: 31px; margin-left: 10px">
              <p style="margin-top: 5px;">发布时间:{{ blog.date }}</p>
            </el-tag>
            <el-button style="float:right;" type="danger" icon="el-icon-delete" @click="handleDelete(blog.id)">删除</el-button>
<!--            <el-button style="float:right; margin-right: 10px" type="primary" icon="el-icon-edit" @click="handleEdit(blog.id)">修改</el-button>-->
          </div>
          <div class="text item" style="float:left;" v-html="blog.content"></div>
        </el-card>
      </div>
    </el-tab-pane>

<!--    <el-tab-pane label="修改" name="third">-->

<!--    </el-tab-pane>-->

  </el-tabs>
</template>

<script>
import RichTextEditor from './RichTextEditor';



export default {
  name: "Article",
  components: {RichTextEditor},
  data(){
    return{
      activeName:'first',
      isGetData:false,
      addArticle:{
        title:"",
        content:"111"
      },
      articleList:'',
    }
  },
  methods:{
    handleClick(){
      this.loadBlog()
    },
    handleEdit(){
      this.activeName='third'
    },
    handleDelete(key){
      this.$axios({
        url: this.$httpUrl + '/blog/delete/'+key,
        method: 'delete',
        headers: {
          token: localStorage.getItem("token")
        },
      }).then((res)=> {
        if (res.data.code === 200) {
          this.$message({
            message:  res.data.msg,
            type: 'success'
          });
          this.loadBlog()
        }else {
          this.$message({
            message: '警告哦，这是一条警告消息' + res.data.msg,
            type: 'warning'
          });
        }
      })
    },
    currentDate(){
      let d = new Date();
      let year = d.getFullYear();
      let month = d.getMonth();
      month = month + 1 > 12 ? 1 : month + 1;
      month =  month > 9 ? month : "0" +month.toString();
      let day = d.getDate();
      let hour = d.getHours();
      hour = hour > 9 ? hour : "0" + hour.toString();
      let minute = d.getMinutes();
      minute = minute > 9 ? minute : "0" + minute.toString();
      let second = d.getSeconds();
      console.log(year+month+day+hour+minute+second)
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    },
    submitArticle(){
      this.$axios({
        url: this.$httpUrl + '/blog/add',
        method: 'post',
        headers: {
          token: localStorage.getItem("token")
        },
        data: {
          "title":this.addArticle.title,
          "content":this.addArticle.content,
          "userId":JSON.parse(localStorage.getItem("user")).nickname,
          "date":this.currentDate()
        }
      }).then((res)=> {
        if (res.data.code === 200) {
          this.$message({
            message: '添加成功',
            type: 'success'
          });
        }else {
          this.$message({
            message: '警告哦，这是一条警告消息' + res.data.msg,
            type: 'warning'
          });
        }
      })
    },
    loadBlog(){
      this.$axios({
        url: this.$httpUrl + '/blog/selectAll',
        method: 'get',
        headers: {
          token: localStorage.getItem("token")
        },
      }).then((res)=> {
        if (res.data.code === 200) {

          this.articleList = res.data.data
          console.log(this.articleList )
        }else {
          this.$message({
            message: '警告哦，这是一条警告消息' + res.data.msg,
            type: 'warning'
          });
        }
      })
    }
  },
  mounted() {
    this.loadBlog();
  },
  created() {
    setTimeout(() => {
      this.addArticle.content = "666";
      this.isGetData = true // 拿到数据以后，再把isGetData置为true，这样的话，组件就会被渲染啦，数据也就会被传递过去啦
    }, 200);
  }
}
</script>

<style scoped>

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}


</style>