package homeworkOne.dataAccess.abstracts;

import homeworkOne.entities.abstracts.IEntity;

public interface IEDevletLogin<T extends IEntity, R extends IEntity> {
	public void validation(T t, R r);
}
