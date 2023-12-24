<script>
import request from "@/utils/request";
import * as XLSX from "xlsx";

export default {
  name: "Class",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/class/find").then(res=>{
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },reset(){
      this.s_clno="";
      this.s_scno="";
      this.s_scname="";
      this.find();
    },
    find(){
      request.get("http://localhost:9090/class/find",{
        params:{
          // clno:this.s_clno,
          clno:this.s_clno,
          scno:this.s_scno,
          scame:this.s_scname,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      request.delete("http://localhost:9090/class/"+id).then(res=>{
        console.log(res)
        if(res.status==="SUCCESS"){
          this.$message.success("删除成功")
          this.load();
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },update(){
      request.post("http://localhost:9090/class/update",this.form).then(res=>{
        if(res.status=="SUCCESS"){
          this.$message.success("更新成功")
          this.dialogUpdateVisible=false;
          this.load();
        }else{
          this.$message.error("更新失败")
        }
      })
      this.dialogFormVisible = false
    },save(){
      request.post("http://localhost:9090/class/add",this.form).then(res=>{
        if(res.status=="SUCCESS"){
          this.$message.success("添加成功")
          this.dialogFormVisible=false;
          this.load();
        }else{
          this.$message.error("添加失败")
        }
      })
      this.dialogFormVisible = false
    },saveFile(){
      this.fileLoadVisible=false;
    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogFormVisible = false;
    },handleFileUploadSuccess(res) {
      if(res.status==="SUCCESS"){
        this.$message.success("添加成功")
        this.fileLoadVisible=false
        this.load()
      }else{
        this.$message.error("添加失败，请重新检查格式")
        this.load()
      }

    },exports(){
      const ws = XLSX.utils.json_to_sheet(this.tableData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
      XLSX.writeFile(wb, '班级信息.xlsx');
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:5,
      s_scno:"",
      s_scname:"",
      s_clno:"",
      dialogVisible: false,
      path:"",
      id:"",
      loc:0,
      page:1,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      dialogFormVisible:false,
      dialogUpdateVisible:false,
      fileLoadVisible:false,
      form:{},
    }
  },
  created() {
    console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.find();
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/manager" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item ><a href='/manager/class' >班级管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-input style="flex:1;width:200px"  placeholder="请输入班级代码" suffix-icon="el-icon-search" v-model="s_clno"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入院系代码" suffix-icon="el-icon-search" v-model="s_scno"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入学院名" suffix-icon="el-icon-search" v-model="s_scname"
                clearable></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" >新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" >文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports" >班级导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="clno" label="班级代码" width="200">
      </el-table-column>
      <el-table-column prop="scno" label="院系代码" width="200">
      </el-table-column>
      <el-table-column prop="scname" label="学院" width="200">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button
              size="mini"
              type="primary"
              @click="dialogUpdateVisible=true">更改信息</el-button>
          <el-button v-if=""
                     size="mini"
                     type="danger"
                     @click="del(scope.row.clno)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <div style="padding:10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="课程信息" :visible.sync="dialogFormVisible" width="30%" :before-close="handleClose">
      <el-form label-width="80px" size="small">
        <el-form-item label="班级">
          <el-input v-model="form.clno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系代码">
          <el-input v-model="form.scno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系名称">
          <el-input v-model="form.scname" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="课程信息" :visible.sync="dialogUpdateVisible" width="30%" :before-close="handleClose">
      <el-form label-width="80px" size="small">
        <el-form-item label="班级">
          <el-input v-model="form.clno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系代码">
          <el-input v-model="form.scno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系名称">
          <el-input v-model="form.scname" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="update">确 定</el-button>
        <el-button @click="dialogUpdateVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="课程信息" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag action="http://localhost:9090/class/import" multiple accept=".xls, .xlsx"
                     :on-success="handleFileUploadSuccess">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button @click="fileLoadVisible = false">取 消</el-button>
      </div>
    </el-dialog>






  </div>
</template>

<style scoped>

</style>