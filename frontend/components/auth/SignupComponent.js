import React, { useState, useEffect } from "react";
import Router from "next/router";
import { signup, isAuth } from "../../actions/auth";

const SignupComponent = () => {
  const [values, setValues] = useState({
    name: "Mayer",
    email: "ela87@naver.com",
    password: "123123123",
    error: "",
    loading: false,
    message: "",
    showForm: true,
  });

  const { name, email, password, error, loading, message, showForm } = values;

  useEffect(() => {
    isAuth() && Router.push('/');
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    setValues({ ...values, loading: true, error: false });
    const user = { name, email, password };

    signup(user)
      .then((data) => {
        if (data.error) {
          setValues({ ...values, error: data.error, loading: false });
        } else {
          setValues({
            ...values,
            name: "",
            email: "",
            password: "",
            error: "",
            loading: false,
            message: data.message,
            showForm: false
          });
        }
      });
  };

  const handleChange = (name) => (e) => {
    setValues({ ...values, error: false, [name]: e.target.value });
  };

  const showLoading = () => loading ? <div className="alert alert-info">로딩...</div> : '';
  const showError = () => error ? <div className="alert alert-danger">{error}</div> : '';
  const showMessage = () => message ? <div className="alert alert-info">{message}</div> : '';

  const signupForm = () => {
    return (
      <form onSubmit={handleSubmit}>
        <div className="form-group m-3">
          <input
            value={name}
            onChange={handleChange("name")}
            type="text"
            className="form-control"
            placeholder="이름을 입력하세요"
          />
        </div>

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
          <button className="btn btn-primary">가입</button>
        </div>
      </form>
    );
  };
  return <React.Fragment>
      {showError()}
      {showLoading()}
      {showMessage()}
      {showForm && signupForm()}
    </React.Fragment>;
};

export default SignupComponent;
