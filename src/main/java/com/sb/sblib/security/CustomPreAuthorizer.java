package com.sb.sblib.security;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Component;

import com.sb.sblib.util.SessionUtil;

/**
 * 権限認証のためのカスタムクラス。
 * 
 * 本クラスはSpringBootのコンポーネントとなっているため、複数のDBテーブルを
 * 別々にクエリして権限認証するような、複雑な認証タイプのロジックを実装できる。
 * なお引数は任意に追加することができる。
 */
@Component
public class CustomPreAuthorizer {

	/**
	 * 権限名を指定して、その権限を持っているか確認する。
	 * 
	 * @param auth 権限名
	 * @return 引数の権限を持っていればtrue、そうでなければfalse
	 */
	public boolean hasAuthority(String auth) {

		// ログインしていない状態であれば、権限を持っていないものと判断する
		String loginUserName = SessionUtil.getLoginUserName();
		if (StringUtil.isBlank(loginUserName)) {
			return false;
		}

		// TODO 本来はまずログインユーザや権限のレコードを取得するDBクエリ処理が必要
		// ここでは簡易検証のため、ログインユーザ名を権限名と扱っている

		// ログインユーザ名が引数と一致すればtrue、それ以外ならfalaseを返却する
		if ("admin".equals(loginUserName)) {
			return true;
		} else {
			return false;
		}
	}
}
