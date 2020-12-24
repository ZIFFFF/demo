layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: '/user/list',
        cellMinWidth: 95,
        page: true,
        // height : "full-120",
        limits: [10, 15, 20, 25],
        // limit: 20,
        id: "userListTable",
        cols: [
            [
                {
                    type: "checkbox"
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: "center",
                    width: 80
                },
                {
                    field: 'no',
                    title: '工号',
                    align: 'center',
                    width: 80
                },
                {
                    field: 'department',
                    title: '部门',
                    align: 'center',
                    width: 100
                },
                {
                    field: 'status',
                    title: '用户状态',
                    align: 'center',
                    width: 100,
                    templet: function (d) {
                        return d.status == 1 ? "正常使用" : "限制使用";
                    }
                },
                {
                    field: 'jobTitle',
                    title: '职务名称',
                    align: 'center',
                    width: 100
                },
                {
                    field: 'lastLoginTime',
                    title: '最后登录时间',
                    align: 'center'
                },
                {
                    field: 'lastLoginIp',
                    title: '最后登录IP地址',
                    align: 'center'
                },
                {
                    title: '操作',
                    templet: '#userListBar',
                    align: "center"
                }
            ]
        ]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("newsListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val() //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加用户",
            type: 2,
            area: ['90%', '90%'],
            fixed: false, //不固定
            maxmin: true,
            content: "/user/add",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".userName").val(edit.username); //登录名
                    body.find(".no").val(edit.no);
                    body.find(".userEmail").val(edit.email); //邮箱
                    body.find(".userTel").val(edit.tel); //会员等级
                    body.find(".userdept input[text=" + edit.department + "]").prop("checked", "checked"); //性别
                    body.find(".userStatus input[value=" + edit.status + "]").prop("checked", "checked"); //用户状态
                    body.find(".jobTitle").val(edit.jobTitle);
                    body.find(".sort").val(edit.sort);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        //layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）

    }

    $(".addNews_btn").click(function () {
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            nos = [];
        if (data.length > 0) {
            for (var i in data) {
                nos.push(data[i].no);
            }
            layer.confirm('确定删除选中的用户？', {
                icon: 3,
                title: '提示信息'
            }, function (index) {
                $.post("/user/del", {
                    no: nos
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'usable') { //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if (_this.text() == "已禁用") {
                usableText = "是否确定启用此用户？",
                    btnText = "已启用";
            }
            layer.confirm(usableText, {

                icon: 3,
                title: '系统提示',
                cancel: function (index) {
                    layer.close(index);
                }
            }, function (index) {
                _this.text(btnText);
                layer.close(index);
            }, function (index) {
                layer.close(index);
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此用户？', {
                icon: 3,
                title: '提示信息'

            }, function (index) {
                $.post("/user/del", {
                    no: data.no  //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.status == 400) {
                        layer.msg(data.message, {
                            icon: 5
                        });
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})
