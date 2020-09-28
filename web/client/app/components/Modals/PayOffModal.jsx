import {Button, Modal, Dropdown, Form, Message} from "semantic-ui-react";
import React from "react";
import ethers from "ethers";
import {Formik} from "formik";
import {clgnTokenAbi, cologneDaoContract, signer} from "../../common/Resources";

export default class PayOffModal extends React.Component {
  state = {
    open: false,
    error: false,
  };

  constructor() {
    super();
    this.formRef = React.createRef();
  }

  openForm = () => {
    this.setState({
      open: true,
    });
  };

  handleSubmit = async (values) => {
    const {tokenAmount} = values;
    const {
      item: {vaultContract},
    } = this.props;

    this.setState({
      error: false,
    });

    const eauTokenAddress = await cologneDaoContract.getEauTokenAddress();

    const eauTokenContract = new ethers.Contract(
      eauTokenAddress,
      clgnTokenAbi,
      signer,
    );

    await eauTokenContract.approve(
      vaultContract.address,
      ethers.utils.parseEther(tokenAmount),
    );

    const res = await vaultContract
      .payOff(ethers.utils.parseEther(tokenAmount))
      .catch((error) => this.setState({error}));

    if (res) {
      this.setState({
        open: false,
        error: false,
      });
    }
  };

  closeForm = () => {
    this.setState({
      error: false,
      open: false,
    });
  };

  formValidate = (values) => {
    const errors = {};

    if (!values.tokenAmount) {
      errors.tokenAmount = "Required";
    }

    return errors;
  };

  render() {
    const {open, error} = this.state;

    return (
      <Modal
        size="tiny"
        onClose={this.closeForm}
        onOpen={this.openForm}
        open={open}
        trigger={<Dropdown.Item>Pay off</Dropdown.Item>}
      >
        <Modal.Header>Pay off vault debt</Modal.Header>
        <Modal.Content>
          <Formik
            innerRef={this.formRef}
            initialValues={{tokenAmount: ""}}
            validate={this.formValidate}
            onSubmit={this.handleSubmit}
          >
            {({
              values,
              errors,
              touched,
              handleChange,
              handleBlur,
              handleSubmit,
            }) => (
              <Form onSubmit={handleSubmit}>
                <Form.Input
                  label="Amount to pay off"
                  fluid
                  error={
                    errors.tokenAmount &&
                    touched.tokenAmount &&
                    errors.tokenAmount
                  }
                  placeholder="100"
                  name="tokenAmount"
                  value={values.tokenAmount}
                  onBlur={handleBlur}
                  onChange={handleChange}
                />
              </Form>
            )}
          </Formik>

          {error && (
            <Message
              error
              header="Something went wrong"
              content={
                (error.data && error.data.message) ||
                (error.message && error.message)
              }
            />
          )}
        </Modal.Content>
        <Modal.Actions>
          <Button color="black" onClick={this.closeForm}>
            Cancel
          </Button>
          <Button
            type="submit"
            content="Pay off"
            color="green"
            onClick={this.formRef.current && this.formRef.current.handleSubmit}
            positive
          />
        </Modal.Actions>
      </Modal>
    );
  }
}
