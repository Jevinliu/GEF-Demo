package wbhgef.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * 创建添加对象的模型工厂
 * 
 * @author software
 *
 */
public class NodeCreationFactory implements CreationFactory {

	private Class<?> template;

	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null) {
			return null;
		}
		if (template == Service.class) {
			Service srv = new Service();
			srv.setEtage(42);
			srv.setName("客房");
			return srv;
		}
		if (template == Employee.class) {
			Employee emp = new Employee();
			emp.setPrenom("祝");
			emp.setName("无双");
			return emp;
		}
		return null;
	}

	@Override
	public Object getObjectType() {
		// TODO Auto-generated method stub
		return template;
	}

}
