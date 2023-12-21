<template>
  <div class="home" style="height: 100%; display: flex; flex-direction: column; flex: 1;">
    <el-container style="height: 100%; border: 1px solid #eee; flex: 1;">
      <el-aside width="sideWidth +'px'" style="background-color: rgb(238, 241, 246); height: 100%; overflow: hidden; box-shadow:2px 0 6px rgba(0,21,41,0.35)">
        <Aside :isCollapse="isCollapse" :collapse="collapse" />
      </el-aside>
      <el-container style="height: 100%;">
        <el-header style="font-size: 12px;">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :open="open"/>
        </el-header>
        <router-view></router-view>
      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import Course from "@/components/Course.vue";
import request from "@/utils/request";
export default {
  name: 'StudentView',
  data(){
    return {
      sideWidth:200,
      isCollapse:false,
      collapseBtnClass:'el-icon-s-fold',
    }
  },
  created(){

  },
  components:{
    Aside,
    Header,
    Course
  },
  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse;
      if(this.isCollapse){
        this.sideWidth=64;
      }
      if(this.collapseBtnClass=='el-icon-s-fold'){
        this.collapseBtnClass='el-icon-s-unfold'
      }else{
        this.collapseBtnClass='el-icon-s-fold'
      }
    },open() {
      this.$alert('这是一段内容', '标题名称', {
        confirmButtonText: '确定'
      });
    }
  }
}
</script>