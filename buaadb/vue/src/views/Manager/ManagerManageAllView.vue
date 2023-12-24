<template>
  <div class="home" style="height: 100%; display: flex; flex-direction: column; flex: 1;">
    <el-container style="height: 100%;">
      <el-main >
        <Person />
      </el-main>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import Course from "@/components/CourseChosen.vue";
import request from "@/utils/request";
import Person from "@/components/Person.vue";
import CourseChosen from "@/components/CourseChosen.vue";
export default {
  name: 'ManagerManageAllView',
  created(){

  },
  components:{
    Aside,
    Header,
    Person
  }
}
</script>