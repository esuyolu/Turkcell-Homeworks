package homeworkOne.dataAccess.abstracts;

import homeworkOne.entities.abstracts.IDto;
import homeworkOne.entities.abstracts.IEntity;

public interface IOrderDao<T extends IEntity, R extends IDto> {
	public void sales(T t, R r);
}
