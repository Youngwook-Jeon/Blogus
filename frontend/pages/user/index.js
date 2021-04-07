import Layout from "../../components/Layout";
import Private from "../../components/auth/Private";
import Link from "next/link";

const UserIndex = () => {
  return (
    <Layout>
      <Private>
        <div className="container-fluid">
          <div className="row">
            <div className="col-md-12 pt-5 pb-5">
              <h2>유저 대시보드</h2>
            </div>
            <div className="col-md-4">
              <ul className="list-group">
                <li className="list-group-item">
                  <Link href="/user/crud/blog">
                    <a>블로그 만들기</a>
                  </Link>
                </li>

                <li className="list-group-item">
                  <Link href="/user/crud/blogs">
                    <a>블로그 수정/삭제하기</a>
                  </Link>
                </li>

                <li className="list-group-item">
                  <Link href="/user/update">
                    <a>프로필 수정하기</a>
                  </Link>
                </li>
              </ul>
            </div>
            <div className="col-md-8">right</div>
          </div>
        </div>
      </Private>
    </Layout>
  );
};

export default UserIndex;
