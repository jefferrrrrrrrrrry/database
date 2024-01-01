<script>
import request from "@/utils/request";
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    name:String,
    collapse:Function,
    open:Function,
    idd:String,
  },
  created() {
    if(this.$route.path.includes("teacher")){
      this.id="教师事务"
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
      console.log(this.idd)
      if(this.idd==="3"){
        window.open("http://localhost:9090/exportlog?token="+localStorage.getItem("token"))
        this.$message.success("导出成功");
      }else{
        this.$message.error(res.msg);
      }

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
      <span class="el-dropdown-link">{{ name }}
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