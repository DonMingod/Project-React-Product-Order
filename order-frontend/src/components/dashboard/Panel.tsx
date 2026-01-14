import type { ReactNode } from "react";

type Props = {
  title?: string;
  toolbar?: ReactNode;
  children: ReactNode;
};

export default function Panel({ title, toolbar, children }: Props) {
  return (
    <div style={{ border: "1px solid #e6e6e6", borderRadius: 6, padding: 12 }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 12 }}>
        <h2 style={{ margin: 0 }}>{title}</h2>
        <div>{toolbar}</div>
      </div>
      <div>{children}</div>
    </div>
    
  );
}
