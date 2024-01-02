<script>
import request from "@/utils/request";
import * as XLSX from "xlsx";

export default {
  name: "Department",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/school/find",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },reset(){
      this.s_clno="";
      this.s_scname="";
      this.find();
    },
    find(){
      request.get("http://localhost:9090/school/find",{
        params:{
          scname:this.s_scname,
          scno:this.s_scno,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      request.delete("http://localhost:9090/school/"+id).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("删除成功")
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      //console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      //console.log(currentPage);
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
      request.post("http://localhost:9090/school/update",this.form).then(res=>{
        if(res.status=="SUCCESS"){
          this.$message.success("更新成功")
          this.dialogUpdateVisible=false;
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
      this.dialogFormVisible = false
    },save(){
      request.post("http://localhost:9090/school/add",this.form).then(res=>{
        if(res.status=="SUCCESS"){
          this.$message.success("添加成功")
          this.dialogFormVisible=false;
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
      this.dialogFormVisible = false
    },handleFileUploadSuccess(res) {
      if(res.status==="SUCCESS"){
        this.$message.success("添加成功")
        this.fileLoadVisible=false
        this.load()
      }else{
        this.$message.error(res.msg)
        this.load()
      }

    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogFormVisible = false;
      this.dialogUpdateVisible=false;
    },exports(){
      request.get("http://localhost:9090/school/find",{
        params:{
          pageNum:1,
          pageSize:10000
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        XLSX.writeFile(wb, '院系信息.xlsx');
        this.find();
      });
      
    }, insert(row) {
      this.dialogUpdateVisible=true;
      this.form = JSON.parse(JSON.stringify(row))
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:5,
      s_scname:"",
      s_scno:"",
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
      fileLoadVisible:false,
      dialogUpdateVisible:false,
      form:{},
      header:{
        token:localStorage.getItem("token"),
      }
    }
  },
  created() {
    //console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.find();
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/manager" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item ><a href='/manager/department' >院系管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-input style="flex:1;width:200px"  placeholder="请输入院系代码" suffix-icon="el-icon-search" v-model="s_scno"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入院系名称" suffix-icon="el-icon-search" v-model="s_scname"
                clearable></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" >新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" >文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports" >院系信息导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="scno" label="院系代码" width="300">
      </el-table-column>
      <el-table-column prop="scname" label="院系名称" width="300">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button
              size="mini"
              type="primary"
              @click="insert(scope.row)">更改信息</el-button>
          <el-button 
               size="mini"
               type="danger"
               @click="del(scope.row.scno)">删除</el-button>
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

    <el-dialog title="院系信息" :visible.sync="dialogFormVisible" width="30%" :before-close="handleClose">
      <el-form label-width="80px" size="small">
        <el-form-item label="院系名称">
          <el-input v-model="form.scname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系代码">
          <el-input v-model="form.scno" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="院系信息" :visible.sync="dialogUpdateVisible" width="30%" :before-close="handleClose">
      <el-form label-width="80px" size="small">
        <el-form-item label="院系代码">
          <el-input v-model="form.scno" autocomplete="off" disabled="true"></el-input>
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
    <el-dialog title="院系信息" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag action="http://localhost:9090/school/import" multiple accept=".xls, .xlsx"
                     :on-success="handleFileUploadSuccess" :headers="header">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
      </div>
    </el-dialog>






  </div>
</template>

<style scoped>

</style>