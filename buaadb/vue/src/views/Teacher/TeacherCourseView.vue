<template>
  <div>
    <el-container style="height: 100%;">
      <el-main >
        <Course />
      </el-main>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Course from "@/components/Course.vue";
export default {
  name: 'TeacherCourseView',
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
