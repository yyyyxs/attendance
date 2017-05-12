$(function () {
    var path = window.location.pathname.split("/")[0];
    $("#msgbox").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("button").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("#addResourceName").on("click", function (e) {
        e.preventDefault();
        $("#resourceTreeDiv").toggleClass("display-hide");
    });
    $("#addRoleSubmitBtn").on("check", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/role/create",
            data: {
                role: $("#addRoleName").val(),
                description: $("#addRoleDesc").val(),
                resourceIds: $("#addRoleIds").val(),
                available: $("input[name='addRoleAvailable']").prop("checked")
            },
            dataType: 'json',
            success: function (data) {//名称修改成功
                $("#createRoleDiv").toggleClass("display-hide");
                $("#roleListDiv").toggleClass("display-hide");
                window.location.href = path + '/role?' + new Date().getTime();
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });
    $("#delRoleSubmitBtn").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/role/delete/" + $("#delRoleId").val(),
            dataType: 'json',
            success: function (data) {//名称修改成功
                $("#delRoleDiv").toggleClass("display-hide");
                $("#roleListDiv").toggleClass("display-hide");
            }
        });
    });

    var delRoleBtnList = $("#roleListDiv #delRoleBtn");
    for (var i = 0; i < delRoleBtnList.length; i++) {
        delRoleBtnList[i].onclick = function () {
            var parentDiv = $(this).parent();
            $("#delRoleDiv").toggleClass("display-hide");
            $("#roleListDiv").toggleClass("display-hide");
            $("#delRoleId").attr("value", parentDiv.attr("roleid"));
            $("#delRoleName").attr("value", parentDiv.attr("rolename"));
            $("#delRoleDesc").attr("value", parentDiv.attr("desc"));
        }
    }
});
function cancelAddRole() {
    $("#createRoleDiv").toggleClass("display-hide");
    $("#roleListDiv").toggleClass("display-hide");
}
function cancelDelRole() {
    $("#delRoleDiv").toggleClass("display-hide");
    $("#roleListDiv").toggleClass("display-hide");
}