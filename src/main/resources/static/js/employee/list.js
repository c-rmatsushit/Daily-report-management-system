function clickBtnDelete() {
    var idck;

    // チェックした要素を取得する
    if (typeof document.frm.idck.length === 'undefined') {
        idck = [{ 'checked': document.frm.idck.checked }];
    } else {
        idck = document.frm.idck;
    }

    // チェックした要素数を数える
    var cnt = 0;
    for (i = 0; i < idck.length; i++) {
        if (idck[i].checked) {
            cnt++;
        }
    }

    if (cnt == 0) {
        alert('従業員が選択されていません。');
        return false;
    }


    if (window.confirm(`${cnt}件削除して良いですか？`)) {
        // OKが押されたら処理を実行
        return true;
    } else {
        return false;
    }
}

// 削除ボタンに関数を割り当てる
document.getElementById("deleteRun").onclick = clickBtnDelete;