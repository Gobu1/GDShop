<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- ���â -->

<!-- �귿 -->
<div class="modal fade" id="exampleModal_item" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1" >
  <div class="modal-dialog" >
    <div class="modal-content" style="width: 530px">
      	<div class="modal-header p-3" id="exampleModalToggleLabel" style="background-color: #4AB34A; color: white; font-weight: bold">
			<h1 class="modal-title fs-5" id="exampleModalLabel"><b>�Ｎ ��÷�� ķ���� ����</b></h1>
		</div>
      <div class="modal-body py-3 px-4 my-3" >
     	<b>�Ｎ ��÷�� ķ���ο� �����ϱ� ���� �Ʒ� ������ Ȯ�����ּ���.</b><br><br>
		<b style="color: #eb2f96">ķ���ο� �����ǽø� 2�ð� ���� �̼Ǽ���ī�忡�� �����ϱ� �̼Ǳ��� �����ּ���.</b><br>
		2�ð� ���� �̼Ǽ���ī�忡�� �����ϱ���� �Ϸ����� �����ø� ���� ���Ÿ� �����ϼ̴��� ķ������ �ڵ� ��ҵǾ� ����Ʈ�� ������ �� ������, ���� ķ���� �������� �Ұ��մϴ�.
      	<div class="pt-3">
		  <input class="form-check-input" type="checkbox" id="check">
		  <label class="form-check-label" for="checkbox">
		    <b>ķ���� ���ǻ����� Ȯ���Ͽ�����, �̿� �����մϴ�. </b>
		  </label>
		</div>
      </div>
      <div class="modal-footer">
     	 <button type="button" class="btn btn-success" data-bs-dismiss="modal" aria-label="Close">���</button>
	     <button type="button" class="btn btn-success" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal" id="apply" disabled data-itemNum-num="${vo.itemNum }">�����ϱ�</button> 
      </div>
    </div>
  </div>
</div>

<div data-bs-backdrop="static" class="modal fade" id="exampleModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header p-3" style="background-color: #4AB34A; color: white; font-weight: bold">
        <h1 class="modal-title fs-5" id="exampleModalToggleLabel2"><b>�Ｎ��÷</b></h1>
      </div>
      <div class="modal-body">
        <img style="margin: 10px 0 20px 85px; width:60%" src="/images/roulette.gif">
		<div id="roulette" style="text-align : center; display: none;"><b style="color: #eb2f96">�����ؿ�!<br>ķ���ο� �����Ǽ̽��ϴ�!</b>
		<div style="font-size: 14px">2�ð� ���� �̼Ǽ���ī�忡�� �����ϱ� �̼Ǳ��� ������ �Ϸ��� �ּ���.<br>�̿Ϸ� �� ķ������ �ڵ� ��ҵǾ� ����Ʈ�� ���� �� ������, <br>���� ķ���� �������� �Ұ��մϴ�.</div></div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-success" data-bs-target="#exampleModalToggle" data-bs-toggle="modal" id="okBtn" 
        onclick="window.location.href=window.location.href">Ȯ��</button>
      </div>
    </div>
  </div>
</div>




<!-- �̼� -->
<div class="modal fade" id="exampleModal_item" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #4AB34A; color: white; font-weight: bold">
				<h1 class="modal-title fs-5" id="exampleModalLabel">
					<b>�̼Ǽ���ī��</b>
				</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body p-3 mb-2 mt-2">
				<div class="d-flex justify-content-center" style="text-align: center">
					<div>
						<div class="mission_order">
							<i class="fa-regular fa-circle-check" style="color: green"></i> �����ϱ�
						</div>
						<div style="font-size: 13px">������</div>
					</div>
					<div class="solid"></div>
					<div>
						<div class="mission_order">
							<i class="fa-regular fa-circle-check" style="color: green"></i> ����
						</div>
						<div style="font-size: 13px">������</div>
					</div>
					<div class="solid"></div>
					<div>
						<div class="mission_order">
							<i class="fa-regular fa-circle-check" style="color: green"></i> ����Ʈ ����
						</div>
						<div style="font-size: 13px">�̿Ϸ�</div>
					</div>
				</div>
				<hr />

				<div class="d-flex justify-content-center">
					<button class="btn btn-outline-success" id="withdraw_btn">����</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/js/item/modal.js"></script>
