<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse:Function,
    open:Function
  },
  created() {
    if(this.$route.path.includes("teacher")){
      this.id="老师事务"
    }else if(this.$route.path.includes("student")){
      this.id="学生事务"
    }else{
      this.id="管理"
    }
  },
  data(){
    return{
      id:""
    }
  },methods:{
    outFile(){
      window.open("http://localhost:9090/exportlog?token="+localStorage.getItem("token"))
    },
    out(){
      this.$router.push("/login");
    }
  }
}
</script>

<template>
  <div style="border-bottom: 1px solid #ccc; line-height: 60px; display:flex">
    <div style="flex:1;font-size:18px">
      <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>

    </div>

    <el-dropdown style="width:70px;cursor:pointer">
      <span class="el-dropdown-link">刘瑞
      <i class="el-icon-arrow-down" style="margin-left:5px"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <el-button type="text" @click="open">个人信息</el-button>
        </el-dropdown-item>
        <el-dropdown-item>
          <el-button type="text" @click="outFile">导出日志</el-button>
        </el-dropdown-item>
        <el-dropdown-item>
          <el-button type="text" @click="out">退出登录</el-button>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  //color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>