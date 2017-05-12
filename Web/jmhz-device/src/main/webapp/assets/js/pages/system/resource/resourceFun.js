$(function () {
    var path = window.location.pathname.split("/")[0];
    //展开对应编号的节点
    $("#resourceTable").treetable({ expandable: true }).treetable("expandNode", 10);

    var appendResourceBtnList = $("#resourceTable #appendResourceBtn");
    var modifyResourceBtnList = $("#resourceTable #modifyResourceBtn");
    var deleteResourceBtnList = $("#resourceTable #deleteResourceBtn");

    for (var i = 0; i < appendResourceBtnList.length; i++) {
        appendResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            //设置parentId为当前节点的Id
            $("#addNodeParentId").attr("value", parentTr.attr("data-tt-id"));
            //设置parentIds
            if (parentTr.attr("data-tt-parent-id") == undefined) {
                $("#addNodeParentIds").attr("value", '0/' + parentTr.attr("data-tt-id") + '/');
            } else {
                $("#addNodeParentIds").attr("value", parentTr.attr("id") + parentTr.attr("data-tt-id") + '/');
            }
            $("input[name='available']").prop("checked", true);
            //设置parentNodeName
            $("#parentNodeName").attr("value", parentTr.attr("name"));
            ShowMask('appendResourceDiv');
        }
    }
    for (var i = 0; i < modifyResourceBtnList.length; i++) {
        modifyResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            $("#modNodeId").attr("value", parentTr.attr("data-tt-id"));
            $("#modNodeParentId").attr("value", parentTr.attr("data-tt-parent-id"));
            $("#modNodeParentIds").attr("value", parentTr.attr("id"));
            $("#nodeName").attr("value", parentTr.attr("name"));
            if (parentTd.attr("resourcetype") == 'menu') {
                $("#nodeType").find("option[value='menu']").attr("selected", true);
            } else {
                $("#nodeType").find("option[value='button']").attr("selected", true);
            }
            if (parentTd.attr("nodeavai") == 'true') {
                $("input[name='nodeAvailable']").prop("checked", true);
            } else {
                $("input[name='nodeAvailable']").prop("checked", false);
            }
            $("#nodeUrl").attr("value", parentTd.attr("nodeurl"));
            $("#nodePermission").attr("value", parentTd.attr("nodeperm"));
            ShowMask('modifyResourceDiv');
        }
    }
    for (var i = 0; i < deleteResourceBtnList.length; i++) {
        deleteResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            $("#delNodeId").attr("value", parentTr.attr("data-tt-id"));
            $("#delNodeName").attr("value", parentTr.attr("name"));
            if (parentTd.attr("resourcetype") == 'menu') {
                $("#delNodeType").find("option[value='menu']").attr("selected", true);
            } else {
                $("#delNodeType").find("option[value='button']").attr("selected", true);
            }
            if (parentTd.attr("nodeavai") == 'true') {
                $("input[name='delNodeAvailable']").prop("checked", true);
            } else {
                $("input[name='delNodeAvailable']").prop("checked", false);
            }
            $("#delNodeParentIds").attr("value", parentTr.attr("id") + parentTr.attr("data-tt-id") + '/');
            $("#delNodeUrl").attr("value", parentTd.attr("nodeurl"));
            $("#delNodePerm").attr("value", parentTd.attr("nodeperm"));
            ShowMask('deleteResourceDiv');
        }
    }

    $("#appendResourceSubmitBtn").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/resource/appendChild",
            data: {
                name: $("#name").val(),
                type: $("#type").val(),
                url: $("#url").val(),
                parentId: $("#addNodeParentId").val(),
                parentIds: $("#addNodeParentIds").val(),
                permission: $("#permission").val(),
                available: $("input[name='available']").prop("checked")
            },
            dataType: 'json',
            success: function (data) {//名称修改成功
                document.location.reload();
                HideMask('appendResourceDiv');
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });

    $("#delResourceSubmitBtn").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/resource/delete",
            data: {
                id: $("#delNodeId").val(),
                parentIds: $("#delNodeParentIds").val()
            },
            dataType: 'json',
            success: function (data) {//名称修改成功
                document.location.reload();
                HideMask('deleteResourceDiv');
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });

    $("#modResourceSubmitBtn").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/resource/update",
            data: {
                id: $("#modNodeId").val(),
                name: $("#nodeName").val(),
                type: $("#nodeType").val(),
                url: $("#nodeUrl").val(),
                parentId: $("#modNodeParentId").val(),
                parentIds: $("#modNodeParentIds").val(),
                permission: $("#nodePermission").val(),
                available: $("#nodeAvailable").val()
            },
            dataType: 'json',
            success: function (data) {//名称修改成功
                document.location.reload();
                HideMask('modifyResourceDiv');
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });
});

function ShowMask(thisObjID) {
    $("#BgDiv").css({ display: "block", height: $(document).height() });
    var yscroll = document.documentElement.scrollTop;
    $("#" + thisObjID).css("top", "100px");
    $("#" + thisObjID).fadeIn("slow");
    document.documentElement.scrollTop = 0;
}

function HideMask(thisObjID) {
    $("#BgDiv").fadeOut("slow");
    $("#" + thisObjID).css("display", "none");
}