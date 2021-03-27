import React, { useState, useEffect } from "react";
import Router from "next/router";
import { signin, authenticate, isAuth } from "../../actions/auth";

const SigninComponent = () => {
  const [values, setValues] = useState({
    email: "mayer@mail.com",
    password: "P4ssword",
    error: "",
    loading: false,
    message: "",
    showForm: true,
  });

  const { email, password, error, loading, message, showForm } = values;

  useEffect(() => {
    isAuth() && Router.push('/');
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    setValues({ ...values, loading: true, error: false });
    const user = { email, password };

    signin(user).then((data) => {
      if (data.error) {
        setValues({ ...values, error: data.error, loading: false });
      } else {
        authenticate(data, () => {
          if (isAuth() && isAuth().role === 1) {
            Router.push(`/admin`);
          } else {
            Router.push(`/user`);
          }
        });
      }
    });
  };

  const handleChange = (name) => (e) => {
    setValues({ ...values, error: false, [name]: e.target.value });
  };

  const showLoading = () =>
    loading ? <div className="alert alert-info">로딩...</div> : "";
  const showError = () =>
    error ? <div className="alert alert-danger">{error}</div> : "";
  const showMessage = () =>
    message ? <div className="alert alert-info">{message}</div> : "";

  const signinForm = () => {
    return (
      <form onSubmit={handleSubmit}>
        <div className="form-group m-3">
          <input
            value={email}
            onChange={handleChange("email")}
            type="email"
            className="form-control"
            placeholder="이메일을 입력하세요"
          />
        </div>

        <div className="form-group m-3">
          <input
            value={password}
            onChange={handleChange("password")}
            type="password"
            className="form-control"
            placeholder="패스워드를 입력하세요"
          />
        </div>

        <div className="m-3">
          <button className="btn btn-primary">로그인</button>
        </div>
      </form>
    );
  };
  return (
    <React.Fragment>
      {showError()}
      {showLoading()}
      {showMessage()}
      {showForm && signinForm()}
    </React.Fragment>
  );
};

export default SigninComponent;
