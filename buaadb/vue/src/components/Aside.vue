<script>
export default {
  name: "Aside",
  props:{
    isCollapse : Boolean,
    collapse: Function,
  },methods:{
    handleSelect(index){
      var now=this.$route.path.toLowerCase();
      var to=this.$router.options.routes[this.loc].path+"/"+this.$router.options.routes[this.loc].children[index-1].path.toLowerCase();
      //console.log(now);
      //console.log(to);
      if(now!==to){
        ////console.log(now);
        ////console.log(to);
        this.$router.push(to);
      }

        //this.$router.push(to);
    }
  },data(){
    return{
      id:"",
      loc:0,
    }
  },created() {
    if(this.$route.path.includes("teacher")){
      this.id="教师事务"
      this.loc=1;
    }else if(this.$route.path.includes("student")){
      this.id="学生事务";
      this.loc=0;
    }else{
      this.id="管理"
      this.loc=2;
    }
  }
}
</script>

<template>
<el-menu :default-openeds="['1', '3']" style="height:100%" background-color="rgb(135,206,235)"
           text-color="#000080" active-text-color="#fff" :collapse-transition="false" :collapse="isCollapse" @select="handleSelect">
    <div style="height:60px; line-height:60px; text-align:center" >
      <img src="../assets/logo.png" alt="" style="width:30px; vertical-align:middle;" v-show="true">
      <b style="color:rgb(0,0,128); vertical-align:middle; "v-show="!isCollapse"> BUAA选课系统</b>
    </div>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-message"></i>
        <span>{{id}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="1" v-if="this.loc==0">选课</el-menu-item>
        <el-menu-item index="2" v-if="this.loc==0">已选课程</el-menu-item>
        <el-menu-item index="1" v-if="this.loc==1">全部课程</el-menu-item>
        <el-menu-item index="2" v-if="this.loc==1">待审核课程</el-menu-item>
        <el-menu-item index="3" v-if="this.loc==1">已开课程</el-menu-item>
        <el-menu-item index="1" v-if="this.loc==2">课程管理</el-menu-item>
        <el-menu-item index="2" v-if="this.loc==2">审核开课</el-menu-item>
        <el-menu-item index="3" v-if="this.loc==2">人员管理</el-menu-item>
        <el-menu-item index="4" v-if="this.loc==2">院系管理</el-menu-item>
        <el-menu-item index="5" v-if="this.loc==2">班级管理</el-menu-item>

      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<style scoped>

</style>